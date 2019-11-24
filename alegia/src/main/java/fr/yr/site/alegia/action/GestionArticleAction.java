package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.beans.ListTaille;
import fr.yr.site.alegia.proxies.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GestionArticleAction extends ActionSupport {

    @Autowired
    MicroserviceArticleProxy microserviceArticleProxy;
    @Autowired
    MicroserviceListTaille microserviceListTaille;
    @Autowired
    MicroserviceTailleProxy microserviceTailleProxy;
    @Autowired
    MicroserviceImageProxy microserviceImageProxy;
    @Autowired
    MicroserviceCategorie microserviceCategorie;

    private Article article;
    private Integer articleId;
    private List<ListTaille> listTailles;
    private List<Image> imageList;
    private List<Categorie> categorieList;

    public String doDetailArticle(){
        try {
            article = microserviceArticleProxy.getArticle(articleId);
            listTailles = microserviceListTaille.findByArticleId(articleId);
            imageList = microserviceImageProxy.findByArticleId(articleId);
            for(ListTaille lt:listTailles){
                lt.setTaille(microserviceTailleProxy.findById(lt.getTailleId()));
            }
            categorieList = microserviceCategorie.findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public List<ListTaille> getListTailles() {
        return listTailles;
    }

    public void setListTailles(List<ListTaille> listTailles) {
        this.listTailles = listTailles;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }
}
