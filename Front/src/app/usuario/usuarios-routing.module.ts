import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuarioPesquisaComponent } from './usuario-pesquisa/usuario-pesquisa.component';
import { UsuarioCadastroComponent } from './usuario-cadastro/usuario-cadastro.component';
import { AuthGuard } from '../seguranca/auth.guard';

const routes: Routes = [
  {
    path: 'pessoas', component: UsuarioPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_PESQUISAR_PESSOA'] }
  },
  {
    path: 'pessoas/nova', component: UsuarioCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_PESSOA'] }
  },
  {
    path: 'pessoas/codigo/:codigo', component: UsuarioCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_PESSOA'] }
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class UsuariosRoutingModule { }
