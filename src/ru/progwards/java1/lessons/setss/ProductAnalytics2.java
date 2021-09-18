package ru.progwards.java1.lessons.setss;


import java.util.*;
public class ProductAnalytics2 {
   private List<Shop> shops;
   private List<Product> products;

   public ProductAnalytics2(List<Product> products, List<Shop> shops) {
      this.products = products;
      this.shops = shops;
   }

//   public Set<Product> existInAll() {
//      Set<Product> allMarketProducts = new HashSet<>(products);
//      for (Product p: allMarketProducts){
//         System.out.println(p.getCode());
//      }
//      for (Shop shop: shops) {
//         for (int i = 0; i < shop.getProducts().size(); i++){
//            System.out.println(shop.getProducts().get(i));
//         }
//         allMarketProducts.retainAll(shop.getProducts());
//         System.out.println(allMarketProducts.size());
//      }
//      return allMarketProducts;
//   }
   public Set<Product> existInAll()
//- товары из products,  которые имеются во всех магазинах
   {
      Set<Product> res = new HashSet<>(products);

      for (Shop shop : shops){
         res.retainAll(shop.getProducts());
   }
      return res;
   }



   public Set<Product> existAtListInOne() {
      Set<Product> oneMarketProducts = new HashSet<>();
      oneMarketProducts.addAll(products);
      return oneMarketProducts;
   }

   public Set<Product> notExistInShops() {
      Set<Product> noMarketProducts = new HashSet<>();
      noMarketProducts.removeAll(products);
      return noMarketProducts;
   }

   public Set<Product> existOnlyInOne() {
      Set<Product> onlyOneMarketProducts = new HashSet<>();
      onlyOneMarketProducts.retainAll(products);
      return onlyOneMarketProducts;
   }

   public static void main(String[] args) {
      List<Product> avail_product = new ArrayList<>();
      avail_product.add(new Product("1"));
      avail_product.add(new Product("3"));
      avail_product.add(new Product("5"));
      avail_product.add(new Product("8"));
      avail_product.add(new Product("2"));

      List<Product> pr1 = new ArrayList<>();
      pr1.add(new Product("1"));
      pr1.add(new Product("2"));
      pr1.add(new Product("3"));
      Shop shop1 = new Shop(pr1);

      List<Product> pr2 = new ArrayList<>();
      pr2.add(new Product("5"));
      pr2.add(new Product("2"));
      pr2.add(new Product("3"));
      Shop shop2 = new Shop(pr2);

      List<Shop> shops = new ArrayList<>();
      shops.add(shop1);
      shops.add(shop2);

      ProductAnalytics2 mainObj = new ProductAnalytics2(avail_product, shops);
      System.out.println("existsinall: " + mainObj.existInAll());

      System.out.println("=================");
      Set<Product> res = new HashSet<>(mainObj.products);
      System.out.println("all: "+res);
      System.out.println("shop1: "+shop2.getProducts());
      if (res.retainAll(shop2.getProducts()))
         System.out.println(true);;
      System.out.println(res);

   }
}
class Product {
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Product)) return false;
      Product product = (Product) o;
      return code.equals(product.code);
   }

   @Override
   public int hashCode() {
      return Objects.hash(code);
   }

   private String code; //уникальный артикул товара

   public Product(String code) {
      this.code = code;
   }

   public String getCode() {
      return code;
   }

   @Override
   public String toString() {
      return "'" + code + '\'';
   }
}

//class Product {
//   private String code;
//
//   public Product(String code) {
//      this.code = code;
//   }
//
//   public String getCode() {
//      return code;
//   }
//
//   @Override
//   public String toString() {
//      return "'" + code + '\'';
//   }
//}

class Shop {
   private List<Product> products;

   public Shop(List<Product> products) {
      this.products = products;
   }

   public List<Product> getProducts(){
      return products;
   }
}