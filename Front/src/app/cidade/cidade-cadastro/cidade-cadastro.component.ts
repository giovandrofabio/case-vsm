import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { LazyLoadEvent, MessageService, ConfirmationService } from 'primeng/api';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { Cidade } from 'src/app/core/models';
import { CidadeService } from '../cidade.service';
@Component({
  selector: 'app-cidade-cadastro',
  templateUrl: './cidade-cadastro.component.html',
  styleUrls: ['./cidade-cadastro.component.css']
})
export class CidadeCadastroComponent implements OnInit {

  cidade = new Cidade();

  constructor(
    private cidadeService: CidadeService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title
  ) { }

  ngOnInit(): void {
    const codigoCidade = this.route.snapshot.params['codigo'];

    this.title.setTitle('Nova Cidade');

    if (codigoCidade) {
      this.carregarCidade(codigoCidade);
    }
  }

  get editando() {
    return Boolean(this.cidade.codigo)
  }

  carregarCidade(codigo: number) {
    this.cidadeService.buscarPorCodigo(codigo)
      .then(cidade => {
        if(cidade)
          this.cidade = cidade;
        this.atualizarTituloEdicao();
      })
      .catch((erro:any) => this.errorHandler.handle(erro));
  }

  salvar(form: NgForm) {
    if (this.editando) {
      this.atualizarCidade(form);
    } else {
      this.adicionarCidade(form);
    }
  }

  adicionarCidade(form: NgForm) {
    this.cidadeService.adicionar(this.cidade)
      .then(() => {
        this.messageService.add({ severity: 'success', detail: 'Cidade adicionada com sucesso!' });
        this.router.navigate(['/cidades']);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarCidade(form: NgForm) {
    this.cidadeService.atualizar(this.cidade)
      .then(cidade => {
        if (cidade)
          this.cidade = cidade;

        this.messageService.add({ severity: 'success', detail: 'Pessoa alterada com sucesso!' });
        this.atualizarTituloEdicao();
        this.router.navigate(['/cidades']);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  nova(form: NgForm) {
    form.reset();

    this.router.navigate(['/cidades/nova']);
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição de cidade: ${this.cidade.nome}`);
  }

}
