package uz.java.uzum.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uz.java.uzum.projection.ProductProjection;

import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class FavouriteRepositoryImpl implements FavouriteRepository {
    @Override
    @Transactional
    public boolean like(Integer userId, Integer productId) {
        return false;
    }

    @Override
    @Transactional
    public List<ProductProjection> getFavouriteByUser(Integer userId) {
        return null;
    }

    @Override
    @Transactional
    public boolean unlike(Integer userId, Integer productId) {
        return false;
    }
}
