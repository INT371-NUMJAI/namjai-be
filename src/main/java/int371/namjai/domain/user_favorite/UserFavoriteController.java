package int371.namjai.domain.user_favorite;


import int371.namjai.domain.user_favorite.mapper.UserFavoriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/view")
@CrossOrigin("*")
public class UserFavoriteController {

    @Autowired
    private UserFavoriteService userFavoriteService;

    @GetMapping(value = "/user-fav")
    public ResponseEntity<List<UserFavorite>> getAllUserFavByEmil(@RequestParam String email) {
        return ResponseEntity.ok().body(userFavoriteService.getUserFavoriteByUserEmail(email));
    }

    @PostMapping(value = "/user-fav/add")
    public ResponseEntity<Void> favorite(@RequestBody UserFavoriteDTO userFavoriteDTO) {
        userFavoriteService.setUserFavorite(userFavoriteDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/user-fav/unfav")
    public ResponseEntity<Void> unFavorite(@RequestParam String type, @RequestParam String refUUID, @RequestParam String email) {
        userFavoriteService.unSetUserFavorite(type, refUUID, email);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/user-fav/check")
    public ResponseEntity<Boolean> checkIsFav(@RequestParam String type, @RequestParam String refUUID, @RequestParam String email) {
        return ResponseEntity.ok().body(userFavoriteService.getUserFavoriteByTypeAndFavoriteRefAndUserEmail(type, refUUID, email));
    }
}
