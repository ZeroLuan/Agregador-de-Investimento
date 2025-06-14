package br.com.business.agregadorinvestimentos.client;

import br.com.business.agregadorinvestimentos.client.dto.BrapiResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/*
OpenFeign (ou apenas Feign) é um cliente HTTP declarativo
e um client binder Java para requisições HTTP. Seu principal
objetivo é simplificar a complexidade na comunicação entre
serviços em arquiteturas distribuídas, especialmente microsserviços.


Em termos mais simples, o OpenFeign permite que você defina uma
interface Java com anotações que representam as requisições HTTP
que você deseja fazer. Ele, então, gera automaticamente a implementação
concreta dessa interface, cuidando de todos os detalhes de baixo nível da comunicação HTTP.
*/

@FeignClient(
        name = "BrapiClient",
        url = "https://brapi.dev"
)
public interface  IBrapiClient {

    @GetMapping(value = "/api/quote/{stockId}")
    BrapiResponseDTO getQuote(@RequestParam("token") String token,
                              @PathVariable("stockId") String stockId);




}
