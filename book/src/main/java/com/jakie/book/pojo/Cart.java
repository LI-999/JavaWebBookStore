package com.jakie.book.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Integer totalCount = 0;
    private BigDecimal totalPrice = new BigDecimal(0);

    private Map<Integer, CartItem> itemMap = new LinkedHashMap<>();

    public Cart() {
    }

    public Cart(Integer totalCount, BigDecimal totalPrice, Map<Integer, CartItem> itemMap) {
        this.totalCount = totalCount;
        this.totalPrice = totalPrice;
        this.itemMap = itemMap;
    }

    /**
     * @return void
     * @Author jakie
     * @Description //TODO  添加商品
     * @Date 10:38 2025/6/28
     * @Param [item] 待添加的商品
     **/
    public void addItem(CartItem item) {
        int id = item.getId();
        CartItem tmp = itemMap.get(id);
        //通过id 判断itemmap中是否存在该商品
        totalCount = totalCount + item.getCount();
//        totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getCount())));
        totalPrice = totalPrice.add(item.getTotalPrice());
        if (tmp != null) {
            //更新商品数量
            item.setCount(tmp.getCount() + item.getCount());
            //更新商品总价
            item.setTotalPrice(tmp.getTotalPrice().add(item.getTotalPrice()));
            itemMap.put(id, item);
        } else {
            itemMap.put(id, item);
        }


    }

    public void deleteItem(Integer id){
        CartItem cartItem = itemMap.get(id);
        totalCount = totalCount - cartItem.getCount();
//        totalPrice = totalPrice.subtract(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        totalPrice = totalPrice.subtract(cartItem.getTotalPrice());
        itemMap.remove(id);
    }

    public void clear(){
        totalPrice = new BigDecimal(0);
        totalCount = 0;
        itemMap.clear();
    }


    public void updateCount(Integer id,Integer count){
        CartItem cartItem = itemMap.get(id);
//        totalPrice.subtract(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        totalPrice.subtract(cartItem.getTotalPrice());
        totalCount = totalCount - cartItem.getCount();

        //修改成新的数量
        cartItem.setCount(count);
        cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));

//        totalPrice.add(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        totalPrice.add(cartItem.getTotalPrice());
        totalCount = totalCount + cartItem.getCount();
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Integer, CartItem> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<Integer, CartItem> itemMap) {
        this.itemMap = itemMap;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + totalCount +
                ", totalPrice=" + totalPrice +
                ", itemMap=" + itemMap +
                '}';
    }
}
