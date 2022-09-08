export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080', //https://vsminformatica-api.herokuapp.com
  tokenAllowedDomains: [ /localhost:8080/ ],
  tokenDisallowedRoutes: [/\/oauth\/token/],
};
