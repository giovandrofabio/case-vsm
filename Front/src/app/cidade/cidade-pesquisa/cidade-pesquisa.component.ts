import { CidadeFiltro, CidadeService } from '../cidade.service';
import { Component, ViewChildren } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { Cidade } from '../../core/models';


@Component({
  selector: 'app-cidade-pesquisa',
  templateUrl: './cidade-pesquisa.component.html',
  styleUrls: ['./cidade-pesquisa.component.css']
})
export class CidadePesquisaComponent {

  totalRegistros = 0;
  filtro = new CidadeFiltro()
  cidades!: Cidade[];
  @ViewChildren('tabela') grid!: Table;

  constructor(
    private cidadeService: CidadeService,
    private errorHandler: ErrorHandlerService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private title: Title) { }

  ngOnInit() {
      this.title.setTitle('Pesquisa de Cidades');
  }

  pesquisar(pagina: number = 0): void {
    this.filtro.pagina = pagina;

    this.cidadeService.pesquisar(this.filtro)
      .then((dados: any) => {
        this.cidades = dados.cidades;
        this.totalRegistros = dados.total;
      });
  }

  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event!.first! / event!.rows!;
    this.pesquisar(pagina);
  }

  confirmarExclusao(cidade: any){
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept: () => {
        this.excluir(cidade);
      }
    });
  }

  excluir(cidade: any){
    this.cidadeService.excluir(cidade.codigo)
      .then(()  => {
          this.pesquisar();
        this.messageService.add({ severity: 'success', detail: 'Cidade excluído com sucesso!' })
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  alternarStatus(cidade: any): void {
    const novoStatus = !cidade.ativo;

    this.cidadeService.mudarStatus(cidade.codigo, novoStatus)
      .then(() => {
        const acao = novoStatus ? 'ativada' : 'desativada';

        cidade.ativo = novoStatus;
        this.messageService.add({ severity: 'success', detail: `Cidade ${acao} com sucesso!` });
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

}
