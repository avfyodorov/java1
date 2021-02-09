package misc.gesiod.sets;


        import java.util.*;

public class ProductAnalytics {
   //список магазинов
   private List<Shop> shops;
   //список всех имеющихся в ассортименте товаров.
   // Все товары, присутствующие в магазинах, обязательно присутствуют в products,
   // но так же тут могут быть и товары, которых нет в магазинах
   private List<Product> products;

   public ProductAnalytics(List<Shop> shops, List<Product> products) {
      this.shops = shops;
      this.products = products;
   }

   //2.13 - товары из products, которые имеются во всех магазинах
   public Set<Product> existInAll(){
      Set<Product> result = new HashSet<>(products);
      for (Shop shop: shops) {
         result.retainAll(shop.getProducts());
      }
      return result;
   }

   //2.14 - товары из products, которые имеются хотя бы в одном магазине
   public Set<Product> existAtListInOne(){
      Set<Product> result = new HashSet<>();
      for (Shop shop: shops) {
         Set<Product> allProducts = new HashSet<>(products);
         allProducts.retainAll(shop.getProducts());
         result.addAll(allProducts);
      }
      return  result;
   }

   //2.15 - товары из products, которых нет ни в одном магазине
   public Set<Product> notExistInShops(){
      Set<Product> ExistAtListInOneSet = existAtListInOne();
      Set<Product> result = new HashSet<>(products);
      result.removeAll(ExistAtListInOneSet);
      return result;
   }

   //2.16 - товары из products, которые есть только в одном магазине
   public Set<Product> existOnlyInOne(){
      Set<Product> result = existAtListInOne();
      // получить пересечение множеств каждого shop  с каждым другим shop
      // и делать removeAll полученных элементов из result
      for (int i = 0; i < shops.size() - 1; i++) {
         for (int j = 0; j < shops.size(); j++) {
            if (i == j){
               continue;
            }
            (shops.get(i).getProducts()).retainAll(shops.get(j).getProducts());
            result.removeAll(shops.get(i).getProducts());
         }
      }
      return result;
   }

   public static void main(String[] args) {
      List<Product> allProducts = new ArrayList<>();
      for(Integer i = 0; i < 20; i++) {
         allProducts.add(new Product(i.toString()));
      }
      List<Shop> allShops = new ArrayList<>();
      for(Integer i = 0; i < 5; i++) {
         List<Product> products = new ArrayList<>();
         for (Integer j = i; j < i + 4; j++) {
            products.add(allProducts.get(j));
         }
         allShops.add(new Shop(products));
      }
      ProductAnalytics productAnalytics = new ProductAnalytics(allShops, allProducts);
      System.out.println(productAnalytics.existInAll());
      System.out.println(productAnalytics.existAtListInOne());
      System.out.println(productAnalytics.notExistInShops());


   }
}

 class Product {
   // уникальный артикул товара
   private String code;
   public Product(String code){
      this.code = code;
   }

   public String getCode() {
      return code;
   }

   @Override
   public String toString() {
      return code;
   }
}

class Shop {
   // товары имеющиеся в магазине
   private List<Product> products;

   public Shop(List<Product> products){
      this.products = products;
   }

   public List<Product> getProducts() {
      return products;
   }

}