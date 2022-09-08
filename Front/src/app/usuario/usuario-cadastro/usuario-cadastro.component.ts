import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { UsuarioService } from '../usuario.service';
import { Title } from '@angular/platform-browser';
import { Pessoa } from 'src/app/core/models';
import { NgForm } from '@angular/forms';
import { CidadeFiltro, CidadeService } from 'src/app/cidade/cidade.service';

@Component({
  selector: 'app-usuario-cadastro',
  templateUrl: './usuario-cadastro.component.html',
  styleUrls: ['./usuario-cadastro.component.css']
})
export class UsuarioCadastroComponent implements OnInit {

  tipos = [
    { label: 'Pessoa Física', value: 'PF'},
    { label: 'Pessoa Jurídica', value: 'PJ'}
  ];

  pessoaFisica: boolean = true;
  pessoaJurica: boolean = true;
  estado = '';
  totalRegistros = 0;
  cidades: any[] = [];
  pessoa = new Pessoa();
  filtro = new CidadeFiltro();

  constructor(
    private pessoaService: UsuarioService,
    private cidadeService: CidadeService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title
  ) { }

  ngOnInit(): void {
    const codigoPessoa = this.route.snapshot.params['codigo'];

    this.title.setTitle('Nova Pessoa');

    if (codigoPessoa) {
      this.carregarPessoa(codigoPessoa);
    }

    this.pesquisarCidades();
  }

  VerificapessoaFisica(){
    this.pessoaFisica = Boolean(this.pessoa.tipo === 'PF')
  }

  get editando() {
    return Boolean(this.pessoa.codigo)
  }

  carregarPessoa(codigo: number) {
    this.pessoaService.buscarPorCodigo(codigo)
      .then(pessoa => {
        if(pessoa)
          this.pessoa = pessoa;
        this.atualizarTituloEdicao();
      })
      .catch((erro:any) => this.errorHandler.handle(erro));
  }

  salvar(form: NgForm) {
    if (this.editando) {
      this.atualizarPessoa(form);
    } else {
      this.adicionarPessoa(form);
    }
  }

  adicionarPessoa(form: NgForm) {
    this.pessoaService.adicionar(this.pessoa)
      .then(() => {
        this.messageService.add({ severity: 'success', detail: 'Pessoa adicionada com sucesso!' });
        this.router.navigate(['/pessoas']);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarPessoa(form: NgForm) {
    this.pessoaService.atualizar(this.pessoa)
      .then(pessoa => {
        if (pessoa)
          this.pessoa = pessoa;

        this.messageService.add({ severity: 'success', detail: 'Pessoa alterada com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  pesquisarCidades(pagina: number = 0): void {
    this.filtro.pagina = pagina;

    this.cidadeService.pesquisar(this.filtro).then(response => {
      this.cidades = response.cidades.map((c: any) => ({ label: c.nome, value: c }));
    });
  }

  teste(form: any){
    console.log(form);
  }

  pesquisando(form: any): void {
    this.filtro.nome = form.filter;
    this.pesquisarCidades();
  }

  nova(form: NgForm) {
    form.reset();

    this.router.navigate(['/pessoas/nova']);
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição de pessoa: ${this.pessoa.nome}`);
  }

  validaCPF(){
    console.log(this.pessoa.cpfcnpj);
  }
}
