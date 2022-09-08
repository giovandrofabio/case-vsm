import { CommonModule, DatePipe ,registerLocaleData } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import localePt from '@angular/common/locales/pt';

import { NgModule } from '@angular/core';

import { TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { ConfirmationService, MessageService } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ToastModule } from 'primeng/toast';

import { ErrorHandlerService } from './error-handler.service';
import { NavbarComponent } from './navbar/navbar.component';

import { PaginaNaoEncontradaComponent } from './pagina-nao-encontrada.component';
import { Title } from '@angular/platform-browser';

import { NaoAutorizadoComponent } from './nao-autorizado.component';
import { AuthService } from '../seguranca/auth.service';

registerLocaleData(localePt, 'pt-BR');

export function HttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    NavbarComponent,
    NaoAutorizadoComponent,
    PaginaNaoEncontradaComponent
  ],
  imports: [
    CommonModule,
    RouterModule,

    ToastModule,
    ConfirmDialogModule,

    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
  ],
  exports: [
    NavbarComponent,

    ToastModule,
    ConfirmDialogModule,
  ],
  providers: [
    DatePipe,
    ErrorHandlerService,

    MessageService,
    ConfirmationService,
    TranslateService,
    AuthService,

    Title
  ]
})
export class CoreModule { }
