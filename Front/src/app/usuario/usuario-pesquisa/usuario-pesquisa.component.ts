import { Component, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { Title } from '@angular/platform-browser';
import { PessoaFiltro, UsuarioService } from '../usuario.service';


@Component({
  selector: 'app-usuario-pesquisa',
  templateUrl: './usuario-pesquisa.component.html',
  styleUrls: ['./usuario-pesquisa.component.css']
})
export class UsuarioPesquisaComponent {

  tipos = [
    { label: 'Pessoa Física', value: 'PF'},
    { label: 'Pessoa Jurídica', value: 'PJ'}
  ];

  pessoaFisica: boolean = true;
  totalRegistros = 0;
  filtro = new PessoaFiltro();
  pessoas: any[] = [];
  @ViewChild('tabela')  grid!: Table;

  constructor(
    private pessoaService: UsuarioService,
    private errorHandler: ErrorHandlerService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private title: Title) { }

  ngOnInit() {
      this.title.setTitle('Gerenciador de Usuarios');
  }

  pesquisar(pagina: number = 0): void {
    this.filtro.pagina = pagina;

    this.pessoaService.pesquisar(this.filtro)
      .then((dados: any) => {
        this.pessoas = dados.pessoas;
        this.totalRegistros = dados.total;
      });
  }

  VerificapessoaFisica(){
    this.pessoaFisica = Boolean(this.filtro.tipo === 'PF')
  }

  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event!.first! / event!.rows!;
    this.pesquisar(pagina);
  }

  confirmarExclusao(pessoa: any){
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept: () => {
        this.excluir(pessoa);
      }
    });
  }

  excluir(pessoa: any){
    this.pessoaService.excluir(pessoa.codigo)
      .then(()  => {
        if (this.grid.first === 0) {
          this.pesquisar();
        } else {
          this.grid.reset();
        }

        this.messageService.add({ severity: 'success', detail: 'Lançamento excluído com sucesso!' })
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  alternarStatus(pessoa: any): void {
    const novoStatus = !pessoa.ativo;

    this.pessoaService.mudarStatus(pessoa.codigo, novoStatus)
      .then(() => {
        const acao = novoStatus ? 'ativada' : 'desativada';

        pessoa.ativo = novoStatus;
        this.messageService.add({ severity: 'success', detail: `Usuario ${acao} com sucesso!` });
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

}
