package ru.gx.core.data.save;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import ru.gx.core.data.DataObject;
import ru.gx.core.data.DataPackage;

import java.sql.SQLException;

@Accessors(chain = true)
public abstract class AbstractJsonDbSavingOperator
        extends AbstractDbSavingOperator {

    protected AbstractJsonDbSavingOperator(
            @NotNull final ObjectMapper objectMapper
    ) {
        super(objectMapper);
    }

    @Override
    protected void internalSavePreparedDataObject(
            @NotNull final Object statement,
            @NotNull final DataObject dataObject
    ) throws SQLException, JsonProcessingException {
        final var data = getObjectMapper().writeValueAsString(dataObject);
        executeStatement(statement, data);
    }

    @Override
    public void internalSavePreparedDataObjects(
            @NotNull final Object statement,
            @NotNull Iterable<DataObject> dataObjects
    ) throws SQLException, JsonProcessingException {
        final var data = getObjectMapper().writeValueAsString(dataObjects);
        executeStatement(statement, data);
    }

    @Override
    public void internalSavePreparedDataPackage(
            @NotNull final Object statement,
            @NotNull DataPackage<?> dataPackage
    ) throws SQLException, JsonProcessingException {
        final var data = getObjectMapper().writeValueAsString(dataPackage);
        executeStatement(statement, data);
    }

    @Override
    public void internalSavePreparedDataPackages(
            @NotNull final Object statement,
            @NotNull Iterable<DataPackage<?>> dataPackages
    ) throws SQLException, JsonProcessingException {
        final var data = getObjectMapper().writeValueAsString(dataPackages);
        executeStatement(statement, data);
    }
}
