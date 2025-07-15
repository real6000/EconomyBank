package org.brokeski.economyBank;

import org.brokeski.economyBank.model.BankAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BankManager {

    private final Map<UUID, BankAccount> accounts = new HashMap<>();

    public BankAccount getAccount(UUID uuid){
        return accounts.computeIfAbsent(uuid, id -> new BankAccount(id, 0.0));
    }

    public void saveAll(){
        //placeholder for file saving logic
    }

    public void unload(){
        accounts.clear();
    }
}
