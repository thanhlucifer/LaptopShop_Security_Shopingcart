package phamvanthanh.example.phamvanthanh_tuan06.service;

import phamvanthanh.example.phamvanthanh_tuan06.model.CartItem;

import java.util.Collection;

public interface ShoppingCartService {
    void add(CartItem newItem);
    void remove(int id);
    CartItem update(int productID, int quantity);
    void clear();
    double getAmount();
    int getCount();
    Collection<CartItem> getAllItems();
}
