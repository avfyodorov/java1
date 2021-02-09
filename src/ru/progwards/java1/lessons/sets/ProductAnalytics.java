package ru.progwards.java1.lessons.sets;

//2.1 Создать класс Product - товар,
//
//        2.2. Создать private String code - уникальный артикул товара
//
//        2.3 Создать конструктор public Product(String code)
//
//        2.4 Метод public String getCode()
//
//        2.5 Создать класс Shop - магазин
//
//        2.6 Создать private List<Product> products - товары имеющиеся в магазине
//
//        2.7 Создать конструктор public Shop(List<Product> products)
//
//        2.8 Создать метод public List<Product> getProducts()
//
//        2.9 Создать класс ProductAnalytics
//
//        2.10 Создать private List<Shop> shops - список магазинов
//
//        2.11 Создать private List<Product> products - список всех имеющихся в ассортименте товаров.
//        Все товары, присутствующие в магазинах, обязательно присутствуют в products,
//        но так же тут могут быть и товары, которых нет в магазинах
//
//        2.12 Создать конструктор  public ProductAnalytics(List<Product> products, List<Shop> shops)
//
//        2.13 Создать функцию public Set<Product> existInAll() - товары из products,
//        которые имеются во всех магазинах
//
//        2.14 Создать функцию public Set<Product> existAtListInOne() - товары из products,
//        которые имеются хотя бы в одном магазине
//
//        2.15 Создать функцию public Set<Product> notExistInShops() - товары из products,
//        которых нет ни в одном магазине
//
//        2.16 Создать функцию public Set<Product> existOnlyInOne() - товары из products,
//        которые есть только в одном магазине
//


import java.util.*;

public class ProductAnalytics {
   private List<Shop> shops;// - список магазинов

   private List<Product> products;// - список всех имеющихся в ассортименте товаров.

   //        Все товары, присутствующие в магазинах, обязательно присутствуют в products,
//        но так же тут могут быть и товары, которых нет в магазинах
//
   public ProductAnalytics(List<Product> products, List<Shop> shops) {
      this.products = products;
      this.shops = shops;
   }

   public Set<Product> existInAll()
//- товары из products,  которые имеются во всех магазинах
   {
      Set<Product> res = new HashSet<>(products);

      for (Shop shop : shops)
         res.retainAll(shop.getProducts());

      return res;
   }

   public Set<Product> existAtListInOne()
   //- товары из products, которые имеются хотя бы в одном магазине
   {
//начинаем с 1-го магазина
      Set<Product> res = new HashSet<>(shops.get(0).getProducts());

//   добавить все остальные
      for (int i = 1; i < shops.size(); i++)
         res.addAll(shops.get(i).getProducts());

      return res;
   }

   public Set<Product> notExistInShops()
//- товары из products,  которых нет ни в одном магазине
   {
      Set<Product> res = new HashSet<>(products);

      for (int i = 0; i < shops.size(); i++)
         res.removeAll(shops.get(i).getProducts());

      return res;
   }

   public Set<Product> existOnlyInOne()
//- товары из products,  которые есть только в одном магазине
   {
      Set<Product> res = new HashSet<>();

//цикл по всем продуктам
      for (Product p : products) {
         int n = 0;

//по всем магазинам
         for (Shop shop : shops) {
            if (shop.getProducts().contains(p))
               n++;
         }

//добавлять если уникальный
         if (n == 1)
            res.add(p);
      }

      return res;
   }

   public static void main(String[] args) {
      List<Product> avail_prods = new ArrayList<>();
      avail_prods.add(new Product("1"));
      avail_prods.add(new Product("3"));
      avail_prods.add(new Product("5"));
      avail_prods.add(new Product("8"));
      avail_prods.add(new Product("2"));

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

      ProductAnalytics mainobj = new ProductAnalytics(avail_prods, shops);
      System.out.println("existsinall: " + mainobj.existInAll());
      System.out.println("existinone : " + mainobj.existAtListInOne());
      System.out.println("not exist  : " + mainobj.notExistInShops());
      System.out.println("exist only : " + mainobj.existOnlyInOne());

   }
}

class Shop //- магазин
{
   private List<Product> products;// - товары имеющиеся в магазине

   public Shop(List<Product> products) {
      this.products = products;
   }

   public List<Product> getProducts() {
      return products;
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
