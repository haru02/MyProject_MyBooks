package com.fcteam6project.mybooks.bookdata;

import java.io.Serializable;

/**
 * Created by froze on 2016-11-22.
 */

public class Item implements Serializable
{
    private String author_t;

    private String sale_price;

    private String cover_s_url;

    private String sale_yn;

    private String pub_date;

    private String link;

    private String barcode;

    private String etc_author;

    private String status_des;

    private String author;

    private String title;

    private String category;

    private String pub_nm;

    private String translator;

    private String description;

    private String isbn;

    private String ebook_barcode;

    private String isbn13;

    private String list_price;

    private String cover_l_url;

    public String getAuthor_t ()
    {
        return author_t;
    }

    public void setAuthor_t (String author_t)
    {
        this.author_t = author_t;
    }

    public String getSale_price ()
    {
        return sale_price;
    }

    public void setSale_price (String sale_price)
    {
        this.sale_price = sale_price;
    }

    public String getCover_s_url ()
    {
        return cover_s_url;
    }

    public void setCover_s_url (String cover_s_url)
    {
        this.cover_s_url = cover_s_url;
    }

    public String getSale_yn ()
    {
        return sale_yn;
    }

    public void setSale_yn (String sale_yn)
    {
        this.sale_yn = sale_yn;
    }

    public String getPub_date ()
    {
        return pub_date;
    }

    public void setPub_date (String pub_date)
    {
        this.pub_date = pub_date;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getBarcode ()
    {
        return barcode;
    }

    public void setBarcode (String barcode)
    {
        this.barcode = barcode;
    }

    public String getEtc_author ()
    {
        return etc_author;
    }

    public void setEtc_author (String etc_author)
    {
        this.etc_author = etc_author;
    }

    public String getStatus_des ()
    {
        return status_des;
    }

    public void setStatus_des (String status_des)
    {
        this.status_des = status_des;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getPub_nm ()
    {
        return pub_nm;
    }

    public void setPub_nm (String pub_nm)
    {
        this.pub_nm = pub_nm;
    }

    public String getTranslator ()
    {
        return translator;
    }

    public void setTranslator (String translator)
    {
        this.translator = translator;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getIsbn ()
    {
        return isbn;
    }

    public void setIsbn (String isbn)
    {
        this.isbn = isbn;
    }

    public String getEbook_barcode ()
    {
        return ebook_barcode;
    }

    public void setEbook_barcode (String ebook_barcode)
    {
        this.ebook_barcode = ebook_barcode;
    }

    public String getIsbn13 ()
    {
        return isbn13;
    }

    public void setIsbn13 (String isbn13)
    {
        this.isbn13 = isbn13;
    }

    public String getList_price ()
    {
        return list_price;
    }

    public void setList_price (String list_price)
    {
        this.list_price = list_price;
    }

    public String getCover_l_url ()
    {
        return cover_l_url;
    }

    public void setCover_l_url (String cover_l_url)
    {
        this.cover_l_url = cover_l_url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [author_t = "+author_t+", sale_price = "+sale_price+", cover_s_url = "+cover_s_url+", sale_yn = "+sale_yn+", pub_date = "+pub_date+", link = "+link+", barcode = "+barcode+", etc_author = "+etc_author+", status_des = "+status_des+", author = "+author+", title = "+title+", category = "+category+", pub_nm = "+pub_nm+", translator = "+translator+", description = "+description+", isbn = "+isbn+", ebook_barcode = "+ebook_barcode+", isbn13 = "+isbn13+", list_price = "+list_price+", cover_l_url = "+cover_l_url+"]";
    }
}