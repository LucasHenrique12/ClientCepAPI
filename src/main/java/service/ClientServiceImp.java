package service;

import model.Address;
import model.AddressRepository;
import model.Client;
import model.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ClientServiceImp  implements ClientService{
private ClientRepository clientRepository;
private AddressRepository addressRepository;
@Autowired
private  CepService cepService;
    public Iterable<Client> searchAll(){
        return clientRepository.findAll();
    }
    public  Client searchId(Long id){
        Optional<Client> client=clientRepository.findById(id);
        return client.get();
    }
    public void insert(Client client){
        saveClientCep(client);
    }
    public void update(Long id, Client client){
    Optional<Client> clientBd = clientRepository.findById(id);
    if (clientBd.isPresent()){
        saveClientCep(client);
    }
    }
    private void saveClientCep(Client client){
        String cep= client.getAddress().getCep();
        Address address= addressRepository.findById(cep).orElseGet(()->{
            Address newAdress=cepService.checkCep(cep);
            addressRepository.save(newAdress);
            return newAdress;
        });
        client.setAddress(address);
        clientRepository.save(client);
    }
    public void delete(Long id){
    clientRepository.deleteById(id);
    }
}
