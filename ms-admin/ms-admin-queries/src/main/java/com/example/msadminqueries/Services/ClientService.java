package com.example.msadminqueries.Services;

import com.example.msadminqueries.DAOs.ClientDAO;
import com.example.msadminqueries.DAOs.UserKDAO;
import com.example.msadminqueries.Proxies.ClientServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientServiceProxy clientServiceProxy;

    public List<ClientDAO> getAllClients() {
        try {
            List<ClientDAO> clientDAOList = new ArrayList<>();
            List<UserKDAO> clientKDAOList = clientServiceProxy.getAllClients();
            clientKDAOList.forEach(userKDAO -> {
                ClientDAO clientDAO = new ClientDAO();
                clientDAO.setClientId(userKDAO.getUserId());
                clientDAO.setClientEmail(userKDAO.getEmail());
                clientDAO.setClientName(userKDAO.getName());
                clientDAO.setShopsNamesist(null);
                clientDAOList.add(clientDAO);
            });
            return clientDAOList;
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    public ClientDAO getClientByClientEmail(String clientEmail) {
        try {
            ClientDAO clientDAO = new ClientDAO();
            if (clientServiceProxy.getClientByClientEmail(clientEmail).isPresent()) {
                UserKDAO userKDAO = clientServiceProxy.getClientByClientEmail(clientEmail).get();
                clientDAO.setClientId(userKDAO.getUserId());
                clientDAO.setClientEmail(userKDAO.getEmail());
                clientDAO.setClientName(userKDAO.getName());
                clientDAO.setShopsNamesist(null);
                return clientDAO;
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    public ClientDAO getClientByClientId(String clientId) {
        try {
            ClientDAO clientDAO = new ClientDAO();
            if (clientServiceProxy.getClientByClientId(clientId).isPresent()) {
                UserKDAO userKDAO = clientServiceProxy.getClientByClientId(clientId).get();
                clientDAO.setClientId(userKDAO.getUserId());
                clientDAO.setClientEmail(userKDAO.getEmail());
                clientDAO.setClientName(userKDAO.getName());
                clientDAO.setShopsNamesist(null);
                return clientDAO;
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

//    public List<ClientDAO> getClientsByName(String clientsName) {
//
//        return clientServiceProxy.getClientsByName(clientsName);
//    }
}