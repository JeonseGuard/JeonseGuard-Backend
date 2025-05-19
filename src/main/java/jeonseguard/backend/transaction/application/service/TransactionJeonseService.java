package jeonseguard.backend.transaction.application.service;

import jeonseguard.backend.transaction.domain.entity.*;
import jeonseguard.backend.transaction.domain.repository.*;
import jeonseguard.backend.transaction.presentation.dto.request.TransactionJeonseAddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionJeonseService {
    private final TransactionJeonseApartmentRepository apartmentRepository;
    private final TransactionJeonseOfficetelRepository officetelRepository;
    private final TransactionJeonseRowhouseRepository rowhouseRepository;

    @Transactional(readOnly = true)
    public List<TransactionJeonseApartment> getTransactionJeonseHistoryForApartment(TransactionJeonseAddressRequest request, List<String> contractYearMonths) {
        return apartmentRepository.findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                contractYearMonths
        );
    }

    @Transactional(readOnly = true)
    public List<TransactionJeonseOfficetel> getTransactionJeonseHistoryForOfficetel(TransactionJeonseAddressRequest request, List<String> contractYearMonths) {
        return officetelRepository.findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                contractYearMonths
        );
    }

    @Transactional(readOnly = true)
    public List<TransactionJeonseRowhouse> getTransactionJeonseHistoryForRowhouse(TransactionJeonseAddressRequest request, List<String> contractYearMonths) {
        return rowhouseRepository.findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                contractYearMonths
        );
    }
}
