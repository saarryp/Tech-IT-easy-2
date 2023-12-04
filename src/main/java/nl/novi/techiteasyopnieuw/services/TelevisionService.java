package nl.novi.techiteasyopnieuw.services;

import nl.novi.techiteasyopnieuw.models.Television;
import nl.novi.techiteasyopnieuw.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelevisionService {
    public TelevisionService() {
    }
//constructor toevoegen om dependency injection uit te voeren


@Autowired
    public TelevisionService(TelevisionRepository televisionRepository) {
}

//public List<Television> getTelevisions() {
//        return ListOfTelevisions.getTelevisions();
//}
}
