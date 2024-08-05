import os

# Obtener el nombre del directorio padre edita "pro.ddsr si es necesario"
# Definir el nombre de la aplicación y la ruta principal
app_group = "pro"
app_domain = "ddsr"
app_name = f"{app_group}.{app_domain}." + os.path.basename(os.path.abspath(os.path.join(os.path.dirname(__file__), "../")))
main_path = os.path.abspath(os.path.join(os.path.dirname(__file__), "../src/main/java/pro/ddsr/backend_dashboard_ecommerce"))


# Definir los arrays de módulos
modules = [
    "account",
    "role",
    "permission",
    "role_permission",
    "account_role",
    "country",
    "region",
    "city",
    "customer",
    "customer_address",
    "customer_phone",
    "order_status",
    "order",
    "supplier",
    "supplier_phone",
    "supplier_address",
    "office",
    "office_phone",
    "office_address",
    "charge",
    "employee",
    "product_gama",
    "product",
    "order_detail",
    "pay_method",
    "transaction"
]

# Función para capitalizar la primera letra de una cadena y transformar texto con guiones bajos
def capitalize(input_string):
    # Convertir el texto a camel case
    result = ""
    capitalize_next = True

    for char in input_string:
        if char == '_':
            # No agregar el guión bajo en el resultado
            capitalize_next = True
        elif capitalize_next:
            # Capitalizar el siguiente carácter
            result += char.upper()
            capitalize_next = False
        else:
            # Mantener el carácter tal cual
            result += char.lower()

    # Asegurarse de que la primera letra sea mayúscula
    if len(result) > 0:
        result = result[0].upper() + result[1:]

    return result

# Función para generar el contenido de los archivos
def get_file_content(file_type, module_name, app_name):
    capitalized_module = capitalize(module_name)

    if file_type == "Dto":
        return f"""
package {app_name}.domain.dto;

public class {capitalized_module}Dto {{
    // Define attributes here

    // Define constructor(s) here

    // Define getter and setter methods here
}}
"""
    elif file_type == "Repository":
        return f"""
package {app_name}.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import {app_name}.persistence.entity.{capitalized_module};
import org.springframework.stereotype.Repository;

@Repository
public interface {capitalized_module}Repository extends JpaRepository<{capitalized_module}, Long> {{
    // Define repository methods here
}}
"""
    elif file_type == "Service":
        return f"""
package {app_name}.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import {app_name}.domain.repository.{capitalized_module}Repository;
import {app_name}.persistence.entity.{capitalized_module};

@Service
public class {capitalized_module}Service {{
    // Define service methods here
    @Autowired
    {capitalized_module}Repository {module_name}Repository;
    
    @Transactional
    public Optional<{capitalized_module}> delete(Long id) {{
        Optional<{capitalized_module}> optional{capitalized_module} = this.{module_name}Repository.findById(id);
        optional{capitalized_module}.ifPresent(
            {capitalized_module}Found -> {{
                this.{module_name}Repository.delete({capitalized_module}Found);
            }}
        );
        return optional{capitalized_module};
    }}
 
    public List<{capitalized_module}> findAll() {{
        return (List<{capitalized_module}>) this.{module_name}Repository.findAll();
    }}

    public Optional<{capitalized_module}> findById(Long id) {{
        return this.{module_name}Repository.findById(id);
    }}

    public {capitalized_module} save({capitalized_module} {capitalized_module}) {{
        return this.{module_name}Repository.save({capitalized_module});
    }}

    public Optional<{capitalized_module}> update(Long id, {capitalized_module} {module_name}) {{
        Optional<{capitalized_module}> optional{capitalized_module} = this.{module_name}Repository.findById(id);
        if (optional{capitalized_module}.isPresent()) {{
            {capitalized_module} {module_name}Item = optional{capitalized_module}.orElseThrow();
            //SETS
            
            return Optional.of(this.{module_name}Repository.save({module_name}Item));
        }}
        return optional{capitalized_module};
    }}
}}
"""
    elif file_type == "Crud":
        return f"""
package {app_name}.persistence.crud;

public interface {capitalized_module}Crud {{
    // Define CRUD methods here
}}
"""
    elif file_type == "Entity":
        return f"""
package {app_name}.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="{module_name}")
public class {capitalized_module} {{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}}
"""
    elif file_type == "Controller":
        return f"""
package {app_name}.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import {app_name}.domain.service.{capitalized_module}Service;
import {app_name}.persistence.entity.{capitalized_module};

import jakarta.validation.Valid;

@RestController
@RequestMapping("/{module_name}")
public class {capitalized_module}Controller {{

    @Autowired
    private {capitalized_module}Service {module_name}Service;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<{capitalized_module}> list{capitalized_module}(){{
        return this.{module_name}Service.findAll();
    }}

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<{capitalized_module}> view(@PathVariable Long id){{
        Optional<{capitalized_module}> optional{capitalized_module}  = {module_name}Service.findById(id);
        if (optional{capitalized_module}.isPresent()){{
            return ResponseEntity.ok(optional{capitalized_module}.orElseThrow());
        }}
        return ResponseEntity.notFound().build();
    }}

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody {capitalized_module} {module_name}, BindingResult result){{
        if (result.hasFieldErrors()) {{
            return validation(result);
        }}
        return ResponseEntity.status(HttpStatus.CREATED).body({module_name}Service.save({module_name}));
    }}

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<{capitalized_module}> update(@PathVariable Long id, @Valid @RequestBody {capitalized_module} {module_name}){{
        Optional<{capitalized_module}> {module_name}Optional = this.{module_name}Service.update(id, {module_name});
        if ({module_name}Optional.isPresent()){{
            return ResponseEntity.status(HttpStatus.CREATED).body({module_name}Optional.orElseThrow());
        }}
        return ResponseEntity.notFound().build();
    }}

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<{capitalized_module}> delete(@PathVariable Long id){{
        //{capitalized_module} {module_name} = new {capitalized_module}();
        //{module_name}.setId(id);
        Optional<{capitalized_module}> optional{capitalized_module} = this.{module_name}Service.delete(id);
        if (optional{capitalized_module}.isPresent()){{
            return ResponseEntity.ok(optional{capitalized_module}.orElseThrow());
        }}
        return ResponseEntity.notFound().build();
    }}

    private ResponseEntity<?> validation(BindingResult result) {{
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {{
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        }});
        return ResponseEntity.badRequest().body(errors);
    }}

}}
"""

# Función para crear la estructura de carpetas y archivos
def create_module_structure(modules, app_name):
    # Definir la ruta base
    base_path = os.path.abspath(os.path.join(os.path.dirname(__file__), main_path))

    # Definir las rutas de carpetas relativas a la ruta base
    paths = [
        "domain/dto",
        "domain/repository",
        "domain/service",
        "persistence/crud",
        "persistence/entity",
        "web/controller"
    ]

    # Crear las carpetas si no existen
    for path in paths:
        full_path = os.path.join(base_path, path)
        os.makedirs(full_path, exist_ok=True)

    # Iterar sobre cada módulo y crear los archivos .java
    for module in modules:
        capitalized_module = capitalize(module)
        
        files = [
            {"Path": os.path.join(base_path, f"domain/dto/{capitalized_module}Dto.java"), "Type": "Dto"},
            {"Path": os.path.join(base_path, f"domain/repository/{capitalized_module}Repository.java"), "Type": "Repository"},
            {"Path": os.path.join(base_path, f"domain/service/{capitalized_module}Service.java"), "Type": "Service"},
            {"Path": os.path.join(base_path, f"persistence/crud/{capitalized_module}Crud.java"), "Type": "Crud"},
            {"Path": os.path.join(base_path, f"persistence/entity/{capitalized_module}.java"), "Type": "Entity"},
            {"Path": os.path.join(base_path, f"web/controller/{capitalized_module}Controller.java"), "Type": "Controller"}
        ]
        
        for file in files:
            if not os.path.exists(file["Path"]):
                with open(file["Path"], "w") as f:
                    content = get_file_content(file["Type"], module, app_name)
                    f.write(content)

# Crear la estructura de módulos
create_module_structure(modules, app_name)

# Definir la carpeta y el archivo config
config_path = os.path.join(main_path, "config")
file_path = os.path.join(config_path, "AppConfig.java")

# Definir el contenido del archivo AppConfig.java
file_content = f"""
package {app_name}.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {{

    @Bean
    public WebMvcConfigurer corsConfigurer() {{
        return new WebMvcConfigurer() {{
            @Override
            public void addCorsMappings(CorsRegistry registry) {{
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }}
        }};
    }}
}}
"""

# Crear la carpeta config si no existe
os.makedirs(config_path, exist_ok=True)

# Crear el archivo AppConfig.java y escribir el contenido
if not os.path.exists(file_path):
    with open(file_path, "w") as f:
        f.write(file_content)

# Definir la carpeta y el archivo config
config_path = os.path.join(main_path, "config")
file_path = os.path.join(config_path, "Securi.java")

# Definir el contenido del archivo AppConfig.java
file_content = f"""
package {app_name}.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {{

    @Bean
    public WebMvcConfigurer corsConfigurer() {{
        return new WebMvcConfigurer() {{
            @Override
            public void addCorsMappings(CorsRegistry registry) {{
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }}
        }};
    }}
}}
"""

# Crear la carpeta config si no existe
os.makedirs(config_path, exist_ok=True)

# Crear el archivo AppConfig.java y escribir el contenido
if not os.path.exists(file_path):
    with open(file_path, "w") as f:
        f.write(file_content)

# Definir la ruta principal para resources
resources_path = os.path.abspath(os.path.join(os.path.dirname(__file__), "../src/main/resources"))
prod_file_path = os.path.join(resources_path, "application-prod.properties")
dev_file_path = os.path.join(resources_path, "application-dev.properties")
main_properties_file_path = os.path.join(resources_path, "application.properties")
sql_file_path = os.path.join(resources_path, "import.sql")

# Definir el contenido para application-dev.properties
dev_content = """
# Server configuration
server.port=8080

# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=campus2023
spring.datasource.password=campus2023
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create

#spring.security.user.name=dev
#spring.security.user.password=123
"""

# Contenido para application.properties
main_properties_content = """
spring.profiles.active=dev
"""

# Crear la carpeta resources si no existe
os.makedirs(resources_path, exist_ok=True)

# Crear el archivo application-prod.properties vacío si no existe
if not os.path.exists(prod_file_path):
    open(prod_file_path, "w").close()

# Crear el archivo application-dev.properties y escribir el contenido si no existe
if not os.path.exists(dev_file_path):
    with open(dev_file_path, "w") as f:
        f.write(dev_content)

# Agregar spring.profiles.active=dev a application.properties si no existe
if not os.path.exists(main_properties_file_path):
    with open(main_properties_file_path, "w") as f:
        f.write(main_properties_content)
else:
    with open(main_properties_file_path, "r") as f:
        current_content = f.read()
    if "spring.profiles.active=dev" not in current_content:
        with open(main_properties_file_path, "a") as f:
            f.write(main_properties_content)

# Crear el archivo import.sql y escribir el contenido si no existe
if not os.path.exists(sql_file_path):
    open(sql_file_path, "w").close()
