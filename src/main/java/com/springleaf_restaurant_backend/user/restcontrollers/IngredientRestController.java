package com.springleaf_restaurant_backend.user.restcontrollers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springleaf_restaurant_backend.security.config.JwtService;
import com.springleaf_restaurant_backend.security.entities.User;
import com.springleaf_restaurant_backend.security.repositories.UserRepository;
import com.springleaf_restaurant_backend.security.service.UserService;
import com.springleaf_restaurant_backend.user.entities.Ingredient;
import com.springleaf_restaurant_backend.user.entities.InventoryBranch;
import com.springleaf_restaurant_backend.user.entities.InventoryBranchIngredient;
import com.springleaf_restaurant_backend.user.entities.OrderThreshold;
import com.springleaf_restaurant_backend.user.entities.Restaurant;
import com.springleaf_restaurant_backend.user.repositories.IngredientRepository;
import com.springleaf_restaurant_backend.user.repositories.InventoryBranchIngredientRepository;
import com.springleaf_restaurant_backend.user.repositories.InventoryBranchRepository;
import com.springleaf_restaurant_backend.user.repositories.OrderThresholdRepository;
import com.springleaf_restaurant_backend.user.repositories.RestaurantRepository;
import com.springleaf_restaurant_backend.user.service.IngredientService;
import com.springleaf_restaurant_backend.user.service.InventoryIngredientService;
import com.springleaf_restaurant_backend.user.service.InventoryService;
import com.springleaf_restaurant_backend.user.service.OrderThresholdService;
import com.springleaf_restaurant_backend.user.service.RestaurantService;
import com.springleaf_restaurant_backend.user.service.mail.MailerService;

@RestController
public class IngredientRestController {
    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private InventoryBranchRepository inventoryBranchRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private OrderThresholdRepository orderThresholdRepository;

    @Autowired
    private MailerService notificationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InventoryBranchIngredientRepository inventoryBranchIngredientRepository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryIngredientService inventoryIngredientService;

    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;

    @Autowired
    private OrderThresholdService orderThresholdService;

    @GetMapping("/public/ingredients")
    public List<Ingredient> getIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/public/ingredient/{ingredientId}")
    public Ingredient getIngredientById(@PathVariable("ingredientId") Long ingredientId) {
        return ingredientService.getIngredientById(ingredientId);
    }

    @PostMapping("/public/create/ingredient")
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.saveIngredient(ingredient);
    }

    @PutMapping("/public/update/ingredient")
    public Ingredient updateIngredient(@RequestBody Ingredient updatedIngredient) {
        return ingredientService.saveIngredient(updatedIngredient);
    }

    @DeleteMapping("/public/delete/ingredient/{ingredientId}")
    public void deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
    }

  
    // @GetMapping("/public/user/getLoggedInUser")
    // public ResponseEntity<User> getLoggedInUser(Authentication authentication) {
    // if (authentication != null && authentication.getPrincipal() instanceof User)
    // {
    // User loggedInUser = (User) authentication.getPrincipal();

    // return ResponseEntity.ok(loggedInUser);
    // } else {

    // return ResponseEntity.ok(null);
    // }
    // }

    // @GetMapping("/public/user/getLoggedInUser")
    // public ResponseEntity<Restaurant> getLoggedInUserRestaurant(Authentication
    // authentication) {
    // if (authentication != null && authentication.getPrincipal() instanceof User)
    // {
    // User loggedInUser = (User) authentication.getPrincipal();
    // Long inventoryBranchId = loggedInUser.getRestaurantBranchId();

    // Restaurant restaurant =
    // restaurantService.getRestaurantByInventoryBranchId(inventoryBranchId);

    // if (restaurant != null) {
    // return ResponseEntity.ok(restaurant);
    // } else {

    // return ResponseEntity.notFound().build();
    // }
    // } else {

    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    // }
    // }

    @GetMapping("/public/user/getLoggedInUser222")
    public ResponseEntity<Map<String, Object>> getLoggedInUserInfoAndIngredientsToReorder(
            Authentication authentication) {
        Map<String, Object> response = new HashMap<>();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User loggedInUser = (User) authentication.getPrincipal();
            Long inventoryId = loggedInUser.getRestaurantBranchId();

            // Lấy thông tin nhà hàng
            Restaurant restaurant = restaurantService.getRestaurantByInventoryBranchId(inventoryId);
            response.put("restaurantName", restaurant.getRestaurantName());

            response.put("loggedInUserName", loggedInUser.getFullName());

            return ResponseEntity.ok(response);

            // Trả về thông báo hoặc response tùy thuộc vào yêu cầu

        } else {
            // Nếu không có người dùng đăng nhập, trả về thông báo hoặc response tùy thuộc
            // vào yêu cầu
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/public/user/getLoggedInUser22222")
    public ResponseEntity<List<String>> getIngredientsBelowThresholdForLoggedInUser(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User loggedInUser = (User) authentication.getPrincipal();
            Long inventoryId = loggedInUser.getRestaurantBranchId(); // Thay đổi tùy theo cách thiết kế cơ sở dữ liệu
                                                                     // của bạn
            // Gọi service để lấy danh sách nguyên liệu dưới ngưỡng đặt hàng
            List<String> ingredientsBelowThreshold = inventoryService.getIngredientsBelowThreshold(inventoryId);

            return ResponseEntity.ok(ingredientsBelowThreshold);
        } else {
            // Xử lý khi không có người dùng đăng nhập
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/public/branch/{branchId}/ingredients")
    public List<InventoryBranchIngredient> getIngredientsInBranch(@PathVariable Long branchId) {
        return inventoryService.getIngredientsInBranch(branchId);
    }

    @GetMapping("/public/restaurant/{restaurantId}/check")
    public List<String> checkThresholdForRestaurant(@PathVariable Long restaurantId) {
        return orderThresholdService.checkThresholdForRestaurant(restaurantId);
    }

    @GetMapping("/public/user/getLoggedInUserOK")
    public ResponseEntity<Object> getLoggedInUserInventoryBranchq(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User loggedInUser = (User) authentication.getPrincipal();
            Long inventoryBranchId = loggedInUser.getRestaurantBranchId();

            // Truy vấn thông tin nhà hàng dựa trên inventoryBranchId
            Restaurant restaurant = restaurantService.getRestaurantByInventoryBranchId(inventoryBranchId);

            if (restaurant != null) {
                Long restaurantId = restaurant.getRestaurantId();
                // Lấy thông tin InventoryBranch từ thông tin nhà hàng
                InventoryBranch inventoryBranch = restaurantService.getInventoryBranchByRestaurantId(restaurantId);

                if (inventoryBranch != null) {
                    // Tạo một đối tượng Map để đóng gói thông tin người dùng, nhà hàng và
                    // inventoryBranch
                    Map<String, Object> responseMap = new HashMap<>();
                    responseMap.put("user", loggedInUser);
                    responseMap.put("restaurant", restaurant);
                    responseMap.put("inventoryBranch", inventoryBranch);

                    return ResponseEntity.ok(responseMap);
                } else {
                    // Trả về thông báo hoặc response tùy thuộc vào yêu cầu
                    return ResponseEntity.notFound().build();
                }
            } else {
                // Trả về thông báo hoặc response tùy thuộc vào yêu cầu
                return ResponseEntity.notFound().build();
            }
        } else {
            // Nếu không có người dùng đăng nhập, trả về thông báo hoặc response tùy thuộc
            // vào yêu cầu
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/public/user/getLoggedInUser")
    public Map<String, Object> checkThresholdForLoggedInUserRestaurant(Authentication authentication) {
        Map<String, Object> responseMap = new HashMap<>();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User loggedInUser = (User) authentication.getPrincipal();
            Long inventoryBranchId = loggedInUser.getRestaurantBranchId();

            // Truy vấn thông tin nhà hàng dựa trên inventoryBranchId
            Restaurant restaurant = restaurantService.getRestaurantByInventoryBranchId(inventoryBranchId);

            if (restaurant != null) {
                // Lấy thông tin InventoryBranch từ thông tin nhà hàng
                InventoryBranch inventoryBranch = restaurantService
                        .getInventoryBranchByRestaurantId(restaurant.getRestaurantId());

                if (inventoryBranch != null) {
                    List<String> ingredientsBelowThreshold = orderThresholdService
                            .checkThresholdForInventoryBranch(inventoryBranch.getInventoryBranchId());

                    responseMap.put("user", loggedInUser);
                    responseMap.put("restaurant", restaurant);
                    responseMap.put("inventoryBranch", inventoryBranch);
                    responseMap.put("ingredientsBelowThreshold", ingredientsBelowThreshold);

                    try {
                        // Gửi email thông báo nguyên liệu dưới ngưỡng đặt lại
                        List<String> missingIngredients = ingredientsBelowThreshold;
                        List<String> restaurantNames = Collections.singletonList(restaurant.getRestaurantName());
                        List<String> userNames = Collections.singletonList(loggedInUser.getFullName());

                        notificationService.sendMissingIngredientsNotification(missingIngredients, restaurantNames,
                                userNames);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        // Xử lý exception khi gửi email không thành công
                    }

                    return responseMap;
                }
            }
        }
        // Nếu không có người dùng đăng nhập hoặc thông tin không khớp, trả về null hoặc
        // thông báo tùy thuộc vào yêu cầu
        return null;
    }

}
