package com.bankai.service;

import com.bankai.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BankAIService {
    @Value("classpath:/data/bank-ai.txt")
    private Resource resource;


    public String prepareFile(Payment payment) throws IOException {
        List<String> prompts=
        Stream.of(
                "Q: Who is the account holder of "+payment.getAccountNumber()+" ?\n",
                 "A: The "+payment.getAccountNumber() +" of Account holder is "+payment.getAccnHolderName() +".\n",
                "Q: How much amount in account number "+payment.getAccountNumber()+" ?\n",
                "A: The amount in account number "+payment.getAccountNumber()+" of "+payment.getAmount()+ ".\n"
                ).collect(Collectors.toList());

        System.out.println(getTextFile().getName());
        FileWriter fw = new FileWriter(getTextFile());

        for(String data:prompts) {
            fw.write(data);
        }
      fw.close();
    return "Data Prepare";
    }




    private File getTextFile() {
        Path path = Paths.get("src", "main", "resources", "data");
        String absolutePath = path.toFile().getAbsolutePath() + "/" + resource.getFilename();
        return new File(absolutePath);
    }

}
