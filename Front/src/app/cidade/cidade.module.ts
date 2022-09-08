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
import { CidadesRoutingModule } from './cidades-routing.module';

import { CidadeCadastroComponent } from './cidade-cadastro/cidade-cadastro.component';
import { CidadePesquisaComponent } from './cidade-pesquisa/cidade-pesquisa.component';

@NgModule({
  declarations: [
    CidadeCadastroComponent,
    CidadePesquisaComponent
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

    SharedModule,
    CidadesRoutingModule
  ],
  exports: []
})
export class CidadeModule { }
