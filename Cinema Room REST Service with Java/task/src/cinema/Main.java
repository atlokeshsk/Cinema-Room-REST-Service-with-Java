package cinema;

import jakarta.validation.Valid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

@RestController
class TheaterController {
    private Theater theater = Theater.getNew(9, 9);


    @GetMapping("/seats")
    public SeatResponse getSeats() {

        var response = new SeatResponse(theater.getRows(), theater.getColumns());
        response.seats = theater.getSeats().stream().filter(seat -> !seat.isBooked()).toList();
        return response;
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> purchase(@Valid @RequestBody PurchaseRequestBody body) {
        // check if the seat
        var seat = theater
                .getSeats()
                .stream()
                .filter(s -> s.getRow() == body.row && s.getColumn() == body.column)
                .findFirst().get();
        if(seat.isBooked()){
            throw new TicketAlreadyPurchasedException("The ticket has been already purchased!");
        }
        String token = UUID.randomUUID().toString();
        theater.purchase(token, seat);
        var response = new PurchaseResponse(token,seat);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket (@RequestBody ReturnRequest body){
        if(!theater.getTickets().containsKey(body.token)){
            throw new TokenNotFoundException("Wrong token!");
        }
        var ticket = theater.returnTicket(body.token);
        var response =  Map.of("ticket", ticket);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> stats(@RequestParam("password") String password){
        if(!password.equals("super_secret")){
            throw new WrongPasswordException("The password is wrong!");
        }
        var response = new StatsResponse(theater.getPurchased(), theater.getAvailable(), theater.getIncome());
        return new  ResponseEntity<>(response,HttpStatus.OK);
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}


