package service.impl;

import com.gohul.TrainService.dto.request.TrainCreateReqDto;
import com.gohul.TrainService.dto.request.TrainUpdateReqDto;
import com.gohul.TrainService.entity.Train;
import com.gohul.TrainService.exception.ResourceAlreadyExistException;
import com.gohul.TrainService.exception.ResourceNotFoundException;
import com.gohul.TrainService.repo.TrainRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.TrainService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {

    private final TrainRepo trainRepo;

    @Override
    public void addTrain(TrainCreateReqDto reqDto) {

        Optional<Train> optionalTrain1 = trainRepo.findByName(reqDto.getName());
        if(optionalTrain1.isPresent())
            throw new ResourceAlreadyExistException(
                    "Train",
                    "TrainName",
                    reqDto.getName()
            );

//        Optional<Train> optionalTrain2 = trainRepo.findBySourceStationAndDestinationStation
//                (
//                    reqDto.getSourceStation(),
//                    reqDto.getDestinationStation()
//                );
//        if(optionalTrain2.isPresent())
//            throw new ResourceAlreadyExistException(
//                    "Train",
//                    "SourceStation And DestinationStation",
//                    reqDto.getSourceStation()+"-->"+reqDto.getDestinationStation()
//            );

        trainRepo.save(
                Train.builder()
                .name(reqDto.getName())
                .sourceStation(reqDto.getSourceStation())
                .destinationStation(reqDto.getDestinationStation())
                .build()
        );


    }

    @Override
    public void updateRoutesForExistingTrain(TrainUpdateReqDto reqDto) {

        Train train = trainRepo.findById(reqDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Train", "TrainID", reqDto.getId().toString()));

        train.setName(reqDto.getName());
        train.setSourceStation(reqDto.getSourceStation());
        train.setDestinationStation(reqDto.getDestinationStation());
        trainRepo.save(train);

    }

    @Override
    public void deleteTrain(Long id) {
        // TODO
    }

    @Override
    public Train getTrainById(Long id) {
        return trainRepo.findById(id).orElse(null);
    }

    @Override
    public List<Train> getTrainListBySourceStationAndDestinationStation(String sourceStation, String destinationStation) {
        return trainRepo.findBySourceStationAndDestinationStation(sourceStation, destinationStation);
    }
}
