package com.likelion.web.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.likelion.web.model.AffiliatedWith;
import com.likelion.web.model.Appointment;
import com.likelion.web.model.Department;
import com.likelion.web.model.Physician;
import com.likelion.web.model.TrainedIn;
import com.likelion.web.model.Undergoes;

public class PhysicianSerializerConfig extends JsonSerializer<Physician> {

    @Override
    public void serialize(Physician physician, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("employeeid", physician.getEmployeeid());
        jsonGenerator.writeStringField("name", physician.getName());
        jsonGenerator.writeStringField("position", physician.getPosition());
        jsonGenerator.writeNumberField("ssn", physician.getSsn());
        
        // Handle departments serialization
        if (physician.getDepartments() != null) {
            List<String> departmentNames = null;
            try {
                departmentNames = physician.getDepartments()
                .stream()
                .map(Department::getName)
                .collect(Collectors.toList());
                jsonGenerator.writeObjectField("departments", departmentNames);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            jsonGenerator.writeNullField("departments");
        }
 
        if (physician.getAffiliatedWith() != null) {
            List<AffiliatedWith> affiliateWithList = null;
            try {
                affiliateWithList = physician.getAffiliatedWith()
                .stream()
                .collect(Collectors.toList());
                jsonGenerator.writeObjectField("affiliatedWith", affiliateWithList);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            jsonGenerator.writeNullField("affiliatedWith");
        }
 
        if (physician.getAppointments() != null) {
            List<Appointment> appointmentList = null;
            try {
                appointmentList = physician.getAppointments()
                .stream()
                .collect(Collectors.toList());
                jsonGenerator.writeObjectField("appointments", appointmentList);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            jsonGenerator.writeNullField("appointments");
        }
 
        if (physician.getPatients() != null) {
            List<?> patients;
            try {
                patients = physician.getPatients()
                .stream()
                .map(s -> s)
                .collect(Collectors.toList());
                jsonGenerator.writeObjectField("patients", patients);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            jsonGenerator.writeNullField("patients");
        }
 
        if (physician.getPrescribes() != null) {
            List<?> prescribes;
            try {
                prescribes = physician.getPrescribes()
                .stream()
                .map(s -> s)
                .collect(Collectors.toList());
                jsonGenerator.writeObjectField("prescribes", prescribes);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            jsonGenerator.writeNullField("prescribes");
        }
 
        if (physician.getUndergoes() != null) {
            List<?> undergoes;
            try {
                undergoes = physician.getUndergoes()
                .stream()
                .map(t -> t)
                .collect(Collectors.toList());
                jsonGenerator.writeObjectField("undergoes", undergoes);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            jsonGenerator.writeNullField("undergoes");
        }
 
        if (physician.getTrainedIn() != null) {
            List<TrainedIn> trainedIns = null;
            try {
                trainedIns = physician.getTrainedIn()
                .stream()
                .map(t -> t)
                .collect(Collectors.toList());
                jsonGenerator.writeObjectField("trainedIn", trainedIns);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            jsonGenerator.writeNullField("trainedIn");
        }
 
        jsonGenerator.writeEndObject();
    }

}
