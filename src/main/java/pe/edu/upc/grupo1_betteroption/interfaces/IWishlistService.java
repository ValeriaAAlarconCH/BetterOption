package pe.edu.upc.grupo1_betteroption.interfaces;

import pe.edu.upc.grupo1_betteroption.dtos.WishlistDto;

import java.util.List;

public interface IWishlistService {
    public WishlistDto grabarWishlist(WishlistDto wishlistdto);

    public List<WishlistDto> getWishlists();
}
