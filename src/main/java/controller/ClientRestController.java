package controller;

import model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import service.ClientService;

public class ClientRestController {
    @Autowired
    private ClientService clientService;

    public ResponseEntity<Iterable<Client>> searchAll(){
        return ResponseEntity.ok(clientService.searchAll());
    }
    public ResponseEntity<Client> searchId(@PathVariable Long id){
        return ResponseEntity.ok(clientService.searchId(id));
    }

    public  ResponseEntity<Client> insert(@RequestBody Client client){
        clientService.insert(client);
        return ResponseEntity.ok(client);
    }
    public ResponseEntity<Client> update(@PathVariable Long id,@RequestBody Client client){
        clientService.update(id,client);
        return ResponseEntity.ok(client);
    }
    public ResponseEntity<Client> update(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }

}
