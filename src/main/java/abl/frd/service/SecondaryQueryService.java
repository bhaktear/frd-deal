package abl.frd.service;
import org.springframework.stereotype.Service;
import java.util.*;
import abl.frd.repository.SecondaryQueryRepository;

@Service 
public class SecondaryQueryService {
    private final SecondaryQueryRepository secondaryQueryRepository;
    public SecondaryQueryService(SecondaryQueryRepository secondaryQueryRepository){
        this.secondaryQueryRepository = secondaryQueryRepository;
    }

    public Map<String, Object> getUser(String userId){
        return secondaryQueryRepository.getUser(userId);
    }
}
