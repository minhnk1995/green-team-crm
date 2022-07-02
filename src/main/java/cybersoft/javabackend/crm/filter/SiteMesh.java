package cybersoft.javabackend.crm.filter;

import javax.servlet.annotation.WebFilter;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

import cybersoft.javabackend.crm.util.UrlConst;
@WebFilter(filterName = "sitemesh" ,urlPatterns = UrlConst.GLOBAL)
public class SiteMesh extends SiteMeshFilter {

}
