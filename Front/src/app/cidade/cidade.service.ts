import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Cidade } from '../core/models';
import { environment } from 'src/environments/environment.prod';


export class CidadeFiltro {
  nome: string | undefined;
  pagina: number = 0;
  itensPorPagina: number = 10;
}

@Injectable({
  providedIn: 'root'
})
export class CidadeService {

  cidadesUrl: string ='';

  constructor(private http: HttpClient) {
    this.cidadesUrl = `${environment.apiUrl}/cidades`
  }

  pesquisar(filtro: CidadeFiltro): Promise<any> {

    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    let params = new HttpParams()
      .set('page', filtro.pagina)
      .set('size', filtro.itensPorPagina);

    if (filtro.nome) {
      params = params.set('nome', filtro.nome);
    }

    return this.http.get(`${this.cidadesUrl}`, { headers, params })
      .toPromise()
      .then((response: any) => {
        const cidades = response['content'];

        const resultado = {
          cidades,
          total: response['totalElements']
        };

        return resultado;
      });
  }

  listarTodas(): Promise<any> {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    return this.http.get(this.cidadesUrl, { headers })
      .toPromise()
      .then((response: any) => {
        const cidades = response['content'];
        return cidades;
      });
  }

  excluir(codigo: number): Promise<void> {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    return this.http.delete<void>(`${this.cidadesUrl}/${codigo}`, { headers })
      .toPromise();
  }

  mudarStatus(codigo: number, ativo: boolean): Promise<void> {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==')
      .append('Content-type','application/json');

    return this.http.put<void>(`${this.cidadesUrl}/${codigo}/ativo`, ativo,{ headers })
    .toPromise();
  }

  adicionar(cidade: Cidade): Promise<Cidade|undefined> {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==')
      .append('Content-Type', 'application/json');

    return this.http.post<Cidade>(this.cidadesUrl, cidade, { headers })
      .toPromise();
  }

  atualizar(cidade: Cidade): Promise<Cidade|undefined> {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==')
      .append('Content-Type', 'application/json');
    return this.http.put<Cidade>(`${this.cidadesUrl}/${cidade.codigo}`, cidade, { headers })
      .toPromise();
  }

  buscarPorCodigo(codigo: number): Promise<Cidade|undefined> {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    return this.http.get<Cidade>(`${this.cidadesUrl}/${codigo}`, { headers })
      .toPromise();
  }
}
