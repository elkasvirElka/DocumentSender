package com.example.a25fli.documentsender;

/**
 * работа с гридом разм больше 1 столца
 */
public class myTableClass {

    String col1;
    String col2;
    String col3;

        /**
         * Конструктор создает новый элемент в соответствии с передаваемыми
         * параметрами:
         * @param h - заголовок элемента
         * @param s - подзаголовок
         */
        myTableClass(String h, String s, String t){
            this.col1=h;
            this.col2=s;
            this.col3=t;
        }

        //Всякие гетеры и сеттеры
        public String getHeader() {
            return col1;
        }
        public void setHeader(String header) {
            this.col1 = header;
        }
        public String getSubHeader() {
            return col2;
        }
        public void setSubHeader(String subHeader) {
            this.col3 = subHeader;
        }
}
