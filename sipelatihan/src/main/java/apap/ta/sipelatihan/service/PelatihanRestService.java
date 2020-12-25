package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PelatihanModel;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PelatihanRestService {
    PelatihanModel createPelatihan(PelatihanModel pelatihan);
}