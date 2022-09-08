import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../seguranca/auth.guard';
import { CidadePesquisaComponent } from './cidade-pesquisa/cidade-pesquisa.component';
import { CidadeCadastroComponent } from './cidade-cadastro/cidade-cadastro.component';

const routes: Routes = [
  {
    path: 'cidades', component: CidadePesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_PESQUISAR_CIDADE'] }
  },
  {
    path: 'cidades/nova', component: CidadeCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_CIDADE'] }
  },
  {
    path: 'cidades/:codigo', component: CidadeCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_CIDADE'] }
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class CidadesRoutingModule { }
