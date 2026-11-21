# language: es
Característica: Flujo completo en OrangeHRM

  Escenario: Login exitoso
    Dado que el usuario está en la página de login de OrangeHRM
    Cuando ingresa el usuario "Admin" y la contraseña "admin123"
    Y hace clic en el botón de login
    Entonces debería visualizar el Dashboard

  Escenario: Buscar empleado
    Dado que el usuario ha iniciado sesión en OrangeHRM
    Cuando navega al módulo PIM
    Y busca el empleado llamado "John"
    Entonces debería ver resultados en la tabla de empleados

  Escenario: Flujo completo E2E
    Dado que el usuario ha iniciado sesión en OrangeHRM
    Cuando navega al módulo PIM
    Y busca el empleado llamado "John"
    Y cierra sesión en la aplicación
    Entonces debería volver a la página de login