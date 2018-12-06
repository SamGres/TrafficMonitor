package si.samgres.api.models;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.UUID;

public class UUIDgenerator {

    public String GetNewUUID()
    {
       return UUID.randomUUID().toString();
    }
}
