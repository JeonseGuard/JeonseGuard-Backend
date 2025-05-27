package jeonseguard.backend.transaction.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jeonseguard.backend.transaction.application.facade.TransactionFacade;
import jeonseguard.backend.transaction.presentation.dto.request.TransactionAddressRequest;
import jeonseguard.backend.transaction.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Transaction", description = "실거래가 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v5/transaction")
public class TransactionController {
    private final TransactionFacade transactionFacade;

    @Operation(summary = "아파트 전세 실거래가 이력 조회", description = "주소 정보를 기반으로, 아파트 전세 실거래가 이력을 조회합니다.")
    @PostMapping("jeonse/apartment")
    public ResponseEntity<List<TransactionJeonseApartmentResponse>> getTransactionJeonseHistoryForApartment(@Valid @RequestBody TransactionAddressRequest request) {
        return ResponseEntity.ok(transactionFacade.getTransactionJeonseHistoryForApartment(request));
    }

    @Operation(summary = "오피스텔 전세 실거래가 이력 조회", description = "주소 정보를 기반으로, 오피스텔 전세 실거래가 이력을 조회합니다.")
    @PostMapping("jeonse/officetel")
    public ResponseEntity<List<TransactionJeonseOfficetelResponse>> getTransactionJeonseHistoryForOfficetel(@Valid @RequestBody TransactionAddressRequest request) {
        return ResponseEntity.ok(transactionFacade.getTransactionJeonseHistoryForOfficetel(request));
    }

    @Operation(summary = "연립다세대 전세 실거래가 이력 조회", description = "주소 정보를 기반으로, 연립다세대 전세 실거래가 이력을 조회합니다.")
    @PostMapping("jeonse/rowhouse")
    public ResponseEntity<List<TransactionJeonseRowhouseResponse>> getTransactionJeonseHistoryForRowhouse(@Valid @RequestBody TransactionAddressRequest request) {
        return ResponseEntity.ok(transactionFacade.getTransactionJeonseHistoryForRowhouse(request));
    }

    @Operation(summary = "아파트 매매 실거래가 이력 조회", description = "주소 정보를 기반으로, 아파트 매매 실거래가 이력을 조회합니다.")
    @PostMapping("sale/apartment")
    public ResponseEntity<List<TransactionSaleApartmentResponse>> getTransactionSaleHistoryForApartment(@Valid @RequestBody TransactionAddressRequest request) {
        return ResponseEntity.ok(transactionFacade.getTransactionSaleHistoryForApartment(request));
    }

    @Operation(summary = "오피스텔 매매 실거래가 이력 조회", description = "주소 정보를 기반으로, 오피스텔 매매 실거래가 이력을 조회합니다.")
    @PostMapping("sale/officetel")
    public ResponseEntity<List<TransactionSaleOfficetelResponse>> getTransactionSaleHistoryForOfficetel(@Valid @RequestBody TransactionAddressRequest request) {
        return ResponseEntity.ok(transactionFacade.getTransactionSaleHistoryForOfficetel(request));
    }

    @Operation(summary = "연립다세대 매매 실거래가 이력 조회", description = "주소 정보를 기반으로, 연립다세대 매매 실거래가 이력을 조회합니다.")
    @PostMapping("sale/rowhouse")
    public ResponseEntity<List<TransactionSaleRowhouseResponse>> getTransactionSaleHistoryForRowhouse(@Valid @RequestBody TransactionAddressRequest request) {
        return ResponseEntity.ok(transactionFacade.getTransactionSaleHistoryForRowhouse(request));
    }
}
