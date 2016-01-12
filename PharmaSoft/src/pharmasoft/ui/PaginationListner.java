/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmasoft.ui;

/**
 *
 * @author Vipula
 */
public interface PaginationListner {
    public void setNoOfPages(int noOfPages);
    public void setCurrentPage();
    public void navigateToNext();
    public void navigateToPrevious();
    public void navigateToFirst();
    public void navigateToLast();
}
