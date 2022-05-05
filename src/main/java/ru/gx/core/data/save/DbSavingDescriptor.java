package ru.gx.core.data.save;

import ru.gx.core.data.DataObject;

public interface DbSavingDescriptor<O extends DataObject> {
    // -----------------------------------------------------------------------------------------------------------------
    // <editor-fold desc="Constants">

    /**
     * Интервалы времени, через которые (1/частота) производится проверка на необходимость сохранения данных
     * (период накопления не может быть меньше данной величины)
     */
    int INTERVAL_MS_FOR_CHECK_NEED_SAVE = 10;

    /**
     * Размер буфера (количество объектов, до которого производится накопление перед сохранением) по умолчанию.
     */
    int DEFAULTS_BUFFER_LIMIT = 1000;

    /**
     * Время накопления (в течение которого данные накапливаются в буфере перед сохранением) по умолчанию.
     */
    int DEFAULTS_BUFFER_FOR_MS = 500;

    DbSavingProcessMode DEFAULT_PROCESS_MODE = DbSavingProcessMode.UseBuffer;

    DbSavingSerializeMode DEFAULT_SERIALIZE_MODE = DbSavingSerializeMode.Json;

    DbSavingAccumulateMode DEFAULT_ACCUMULATE_MODE = DbSavingAccumulateMode.ListOfObjects;
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------------
    // <editor-fold desc="Properties">

    /**
     * @return класс объектов сохраняемых данных
     */
    Class<O> getDataObjectClass();

    /**
     * @return SQL сохранения в БД
     */
    String getSaveCommand();

    /**
     * @return Режим обработки данных (допускается или нет буферизация)
     */
    DbSavingProcessMode getProcessMode();

    /**
     * @return способ представления данных при сериализации (отправке данных в БД)
     */
    DbSavingSerializeMode getSerializeMode();

    /**
     * @return Режим накопления и отправки данных в БД. При значении == None свойства BufferLimit и BufferForMs
     * не имеют смысла
     */
    DbSavingAccumulateMode getAccumulateMode();

    /**
     * @return Признак того, что буфер пуст
     */
    boolean bufferIsEmpty();

    /**
     * @return Максимальный размер буфера, по достижении которого данные будут сохранены в БД.
     */
    int getBufferLimit();

    /**
     * @return Максимальное время (в мс), в течение которого требуется копить данные, после этого данные будут сохранены в БД.
     */
    int getBufferForMs();

    /**
     * @return Сколько миллисекунд прошло последнего сохранения в БД данных (= System.currentTimeMillis() - lastSavedTimeMillis).
     */
    long getLastSavedIntervalMs();

    /**
     * @return или накоплен буфер достаточного размера, или прошло достаточно времени накопления.
     */
    boolean readyForSave();

    /**
     * @return Инициализировал ли описатель. После инициализации нельзя менять ряд параметров.
     */
    boolean isInitialized();

    /**
     * Инициализация. Описание дескриптора должно заканчиваться данным методом
     */
    void init();
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------------
}