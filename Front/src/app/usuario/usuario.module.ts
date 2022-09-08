import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ButtonModule } from 'primeng/button';
import { InputMaskModule } from 'primeng/inputmask';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { InputNumberModule } from 'primeng/inputnumber';
import { SharedModule } from '../shared/shared.module';

import { UsuarioPesquisaComponent } from './usuario-pesquisa/usuario-pesquisa.component';
import { UsuarioCadastroComponent } from './usuario-cadastro/usuario-cadastro.component';
import { UsuariosRoutingModule } from './usuarios-routing.module';
import { DropdownModule } from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import { SelectButtonModule } from 'primeng/selectbutton';

@NgModule({
  declarations: [
    UsuarioPesquisaComponent,
    UsuarioCadastroComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,

    InputTextModule,
    ButtonModule,
    TableModule,
    TooltipModule,
    InputNumberModule,
    InputMaskModule,
    DropdownModule,
    InputSwitchModule,
    SelectButtonModule,

    SharedModule,
    UsuariosRoutingModule,
  ],
  exports: []
})
export class UsuarioModule { }
