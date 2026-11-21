package steps;

import hooks.Hooks;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;

public class FlujoPrincipalSteps {

    private final WebDriver driver = Hooks.getDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private final DashboardPage dashboardPage = new DashboardPage(driver);
    private final PIMPage pimPage = new PIMPage(driver);

    @Dado("que el usuario está en la página de login de OrangeHRM")
    public void queElUsuarioEstaEnLaPaginaDeLogin() {
        loginPage.gotoPage();
        Assert.assertTrue(loginPage.isOnLoginPage(), "No se cargó correctamente la página de login");
    }

    @Cuando("ingresa el usuario {string} y la contraseña {string}")
    public void ingresaUsuarioYContrasena(String usuario, String contrasena) {
        loginPage.enterUserName(usuario);
        loginPage.enterPassword(contrasena);
    }

    @Cuando("hace clic en el botón de login")
    public void haceClicEnElBotonDeLogin() {
        loginPage.clickLogin();
    }

    @Entonces("debería visualizar el Dashboard")
    public void deberiaVisualizarElDashboard() {
        Assert.assertTrue(dashboardPage.isOnDashboard(), "El usuario no ingresó al Dashboard");
        Assert.assertFalse(dashboardPage.getHeaderText().isEmpty(), "El header del Dashboard está vacío");
    }

    @Dado("que el usuario ha iniciado sesión en OrangeHRM")
    public void queElUsuarioHaIniciadoSesion() {
        loginPage.gotoPage();
        loginPage.loginAs("Admin", "admin123");
        Assert.assertTrue(dashboardPage.isOnDashboard(), "No se pudo iniciar sesión en OrangeHRM");
    }

    @Cuando("navega al módulo PIM")
    public void navegaAlModuloPIM() {
        dashboardPage.goToPIM();
        Assert.assertTrue(pimPage.isOnPIMPage(), "No se cargó la página del módulo PIM");
    }

    @Cuando("busca el empleado llamado {string}")
    public void buscaElEmpleadoLlamado(String nombreEmpleado) {
        pimPage.searchEmployeeByName(nombreEmpleado);
    }

    @Entonces("debería ver resultados en la tabla de empleados")
    public void deberiaVerResultadosEnLaTabla() {
        Assert.assertFalse(pimPage.isNoRecordsDisplayed(), "Se mostró 'No Records Found' al buscar el empleado");
        Assert.assertTrue(pimPage.hasResults(), "No se encontraron resultados en la tabla");
    }

    @Cuando("cierra sesión en la aplicación")
    public void cierraSesionEnLaAplicacion() {
        dashboardPage.logout();
    }

    @Entonces("debería volver a la página de login")
    public void deberiaVolverALaPaginaDeLogin() {
        Assert.assertTrue(loginPage.isOnLoginPage(), "No volvió a la página de login tras cerrar sesión");
    }
}