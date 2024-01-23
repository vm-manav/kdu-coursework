package org.example.managers;
import org.example.utils.Tyre;
import org.springframework.stereotype.Component;

@Component
public class TyreService {
    public Tyre getTyre(String tyreName) {
        if(tyreName.equalsIgnoreCase("Bridgestone")) {
            return new Tyre(tyreName,8000.00);
        } else if (tyreName.equalsIgnoreCase("MRF")) {
            return new Tyre(tyreName,6000.00);
        } else {
            return null;
        }
    }
}
