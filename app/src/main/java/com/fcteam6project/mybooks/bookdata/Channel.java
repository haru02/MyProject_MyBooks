package com.fcteam6project.mybooks.bookdata;

import java.util.List;

/**
 * Created by froze on 2016-11-22.
 */

public class Channel
{
    private String result;

    private String title;

    private String totalCount;

    private String description;

    private String link;

    private String lastBuildDate;

    private List<Item> item;

    private String generator;

    public String getResult ()
    {
        return result;
    }

    public void setResult (String result)
    {
        this.result = result;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getTotalCount ()
    {
        return totalCount;
    }

    public void setTotalCount (String totalCount)
    {
        this.totalCount = totalCount;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getLastBuildDate ()
    {
        return lastBuildDate;
    }

    public void setLastBuildDate (String lastBuildDate)
    {
        this.lastBuildDate = lastBuildDate;
    }

    public List<Item> getItem ()
    {
        return item;
    }

    public void setItem (List<Item> item)
    {
        this.item = item;
    }

    public String getGenerator ()
    {
        return generator;
    }

    public void setGenerator (String generator)
    {
        this.generator = generator;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", title = "+title+", totalCount = "+totalCount+", description = "+description+", link = "+link+", lastBuildDate = "+lastBuildDate+", item = "+item+", generator = "+generator+"]";
    }
}