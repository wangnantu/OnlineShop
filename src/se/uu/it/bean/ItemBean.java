package se.uu.it.bean;

public class ItemBean {
	private int id;
	private String name;
	private float price;
	private int stock;
	
                         public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
                          public float getPrice() {
                                                  return price;
                          }

                          public void setPrice(float price) {
                                                    this.price = price;
                          }
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
                           
//        public String getXml(){
//            StringBuffer xmlOut = new StringBuffer();
//            xmlOut.append("<item>");
//            xmlOut.append("<id>");
//            xmlOut.append(id);
//            xmlOut.append("</id>");
//            xmlOut.append("<name><![CDATA[");
//            xmlOut.append(name);
//            xmlOut.append("]]></name>");
//            xmlOut.append("<price>");
//            xmlOut.append(price);
//            xmlOut.append("</price>");
//            xmlOut.append("<stock>");
//            xmlOut.append(stock);
//            xmlOut.append("</stock>");
//            xmlOut.append("</item>");
//            
//            return xmlOut.toString();
//        }
}
