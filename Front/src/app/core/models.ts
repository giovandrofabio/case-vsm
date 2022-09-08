export class Endereco {
  logradouro?: string;
  numero?: string;
  complemento?: string;
  bairro?: string;
  cep!: string;
}

export class Cidade {
  codigo?: number;
  uf?: string;
  nome?: string;
}

export class Pessoa {
  codigo?: number;
  nome?: string;
  tipo = 'PF';
  cpfcnpj?: string;
  endereco = new Endereco();
  ativo = true;
  cidade? = new Cidade();
  telefone?: string;
  celular?: string;
  email?: string;
  senha?: string;
}


