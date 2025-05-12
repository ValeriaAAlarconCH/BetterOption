package pe.edu.upc.grupo1_betteroption.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.grupo1_betteroption.dtos.WishlistDto;
import pe.edu.upc.grupo1_betteroption.entities.Wishlist;
import pe.edu.upc.grupo1_betteroption.interfaces.IWishlistService;
import pe.edu.upc.grupo1_betteroption.repositories.WishlistRepository;

import java.util.List;

@Service
public class WishlistService implements IWishlistService {
    @Autowired
    private WishlistRepository wishlistrepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public WishlistDto grabarWishlist(WishlistDto wishlistdto) {
        Wishlist wishlist = modelMapper.map(wishlistdto, Wishlist.class);
        Wishlist guardar = wishlistrepository.save(wishlist);
        return modelMapper.map(guardar, WishlistDto.class);
    }

    @Override
    public List<WishlistDto> getWishlists() {
        return wishlistrepository.findAll().stream()
                .map(wishlist -> modelMapper.map(wishlist, WishlistDto.class))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (wishlistrepository.existsById(id)) {
            wishlistrepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró el wishlist con ID: " + id);
        }
    }
}
