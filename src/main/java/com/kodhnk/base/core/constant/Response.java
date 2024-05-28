package com.kodhnk.base.core.constant;

public enum Response {

    SUCCESS("Başarılı"),
    CREATED("Oluşturuldu"),
    REMOVED("Silindi"),
    UPDATED("Güncellendi"),
    DATALISTED("Veri Listelendi"),

    USER_CREATED("Kullanıcı oluşturuldu"),
    USER_UPDATED("Kullanıcı güncellendi"),
    USER_ALREADY_EXISTS("Kullanıcı zaten mevcut"),
    USER_NOT_CREATED("Kullanıcı oluşturulamadı"),

    LOGIN_SUCCESS("Giriş Başarılı"),
    LOGIN_FAILED("Giriş Başarısız"),

    ERROR("Hata"),
    NOT_FOUND("Bulunamadı"),
    UNAUTHORIZED("Yetkisiz"),
    FORBIDDEN("Yasak"),
    BAD_REQUEST("Geçersiz İstek"),
    INTERNAL_SERVER_ERROR("Sunucu Hatası"),
    GETALL_PATIENT("Hastalar Listelendi"),
    PATIENT_NOT_FOUND("Hasta Bulunamadı"),
    USER_LIST("Kullanıcılar Listelendi"),
    USER_NOT_FOUND("Kullanıcı Bulunamadı"),
    DOCTOR_NOT_FOUND("Doktor Bulunamadı"),
    GET_DOCTOR("Doktorlar Listelendi"),
    HOSPITAL_NOT_FOUND("Hastane Bulunamadı"),
    DELETE_DOCTOR("Doktor Silindi"),
    DEPARTMENT_LISTED("Departmanlar Listelendi"),
    DEPARTMENT_FOUND("Departman Bulundu"),
    DEPARTMENT_NOT_FOUND("Departman Bulunamadı"),
    CREATE_DEPARTMENT("Departman Eklendi"),
    UPDATE_DEPARTMENT("Departman Güncellendi"),
    DELETE_DEPARTMENT("Departman Silindi"),
    HOSPITAL_GET_ALL("Hastaneler Listelendi"),
    HOSPITAL_BY_ID("Hastane Getirildi"),
    DELETE_HOSPITAL("Hastane Silindi"),
    UPDATE_HOSPITAL("Hastane Güncellendi"),
    CREATE_HOSPITAL("Hastane Eklendi"),
    CREATE_DOCTOR("Doktor Eklendi"),
    UPDATE_DOCTOR("Doktor Güncellendi"),
    GET_PATIENT("Hasta Getirildi"),
    CREATE_PATIENT("Hasta Eklendi"),
    DELETE_PATIENT("Hasta Silindi"),
    UPDATE_PATIENT("Hasta Bilgileri Güncellendi"),
    GET_EXAMINATION("Muayene Getirildi"),
    EXAMINATION_NOT_FOUND("Muayene Bulunamadı"),
    GET_APPOINTMENT("Randevular Listelendi"),
    APPOINTMENT_NOT_FOUND("Randevu Bulunamadı"),
    MEDICINE_LISTED("İlaçlar Listelendi"),
    MEDICINE_CREATED("İlaç Eklendi"),
    MEDICINE_UPDATED("İlaç Güncellendi"),
    MEDICINE_DELETED("İlaç Silindi"),
    MEDICINE_NOT_FOUND("İlaç Bulunamadı"),
    MEDICINE_FOUND("İlaç Getirildi."),
    CREATE_APPOINTMENT("Randevu Eklendi."),
    INVALID_PATIENT_OR_DOCTOR("Doktor veya Hasta Bulunamadı."),
    APPOINTMENT_DELETE("Randevu Silindi."),
    APPOINTMENT_UPDATE("Randevu Güncellendi."),
    INVALID_SELECTION("Geçersiz Seçim"),
    CREATE_EXAMINATION("Muayene Eklendi."),
    CREATE_MEDICINE("İlaç Eklendi."),
    UPDATE_MEDICINE("İlaç Güncellendi."),
    DELETE_MEDICINE("İlaç Silindi."),
    GET_MEDICINE("İlaç Getirildi."),
    GET_ALL_MEDICINE("Tüm İlaçlar Listelendi."),
    GET_ALL_APPOINTMENT("Tüm Randevular Listelendi."),

    EMAIL_ALREADY_EXISTS("Kayıt Bulunmaktadır.");

    private final String message;

    Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}