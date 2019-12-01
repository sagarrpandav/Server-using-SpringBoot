package com.evaluation.demo.controller;

import com.evaluation.demo.dto.HackathonDto;
import com.evaluation.demo.service.HackathonServiceImplementation;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class HackathonController {
    @Autowired
    private HackathonServiceImplementation hackathonServiceImplementation;

    @GetMapping(value = "/hackathons")
    public List<HackathonDto> getAllHackathons() {
        return hackathonServiceImplementation.getAllHackathons();
    }

    @GetMapping(value = "/elastic")
    public void entryElasticData() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("kbn-version", "6.6.1");

        String s = "{" + "'id': 214358," + "'version': 7, " + "      'campaignName': 'campaign Fee grid', " + "      'name': 'campaign Fee grid', "
                + "      'campaignFlightStart': '2017-01-01', " + "      'campaignFlightEnd': '2017-12-31', " + "      'publicId': 'CP7C0Z', " + "      'budget': 10000, "
                + "      'status': 'ACTIVE', " + "      'currencyCode': 'GBP', " + "      'budgetCurrencyTypeId': 1, " + "      'isBudgetApproved': false, "
                + "      'isBudgetAuthorized': true, " + "      'campaignState': { " + "        'id': 0, " + "        'version': 0, "
                + "        'autoUpdateFinancialsFlag': 0, " + "        'isBudgetAuthorizedOverride': false, " + "        'isBudgetAuthorizationActive': true, "
                + "        'isIOLevelApprovalEnabled': false, " + "        'isIOApprovalRestrictedToPlannedAmount': false, " + "        'reconciliationEnabled': false "
                + "      }, " + "      'campaignPrint': { " + "        'id': 0, " + "        'version': 0 " + "      }, " + "      'campaignTV': { " + "        'id': 0, "
                + "        'version': 0 " + "      }, " + "      'campaignDigital': { " + "        'id': 0, " + "        'version': 0 " + "      }, "
                + "      'campaignBuyDetails': [], " + "      'campaignAdditionalProviders': [], " + "      'campaignReferenceIdList': [], " + "      'isMine': true, "
                + "      'ratePrecision': 4, " + "      'isDualCostEnabled': false, " + "      'ipaEnabled': false, " + "      'ipaLinked': false, "
                + "      'canModifyBuyView': false, " + "      'canUnlink': false, " + "      'budgetApprovalStatus': 'NOT_SUBMITTED', " + "      'buyTotal': 23313.51, "
                + "      'plannedTotal': 23348.72, " + "      'budgetApprovalActive': true, " + "      'budgetControlActive': true, "
                + "      'budgetAuthorizedActive': false, " + "      'needsAttentionProposals': 0, " + "      'needsAttentionIOs': 8, " + "      'needsAttentionAdServers': 2, "
                + "      'needsAttentionFinancials': 21, " + "      'multiCurrencyEnabled': true, " + "      'multiCurrency': false, " + "      'mediumCodes': 'O', "
                + "      'isNewAutoCreatedCampaign': 0, " + "      'autoCreated': false, " + "      'radia': false, " + "      'HomepageBuyDetails': [], "
                + "      'isBillableFeeEnabled': true " + "    }";


        
        HttpEntity entity = new HttpEntity(s,httpHeaders);

       /* ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:5601/api/console/proxy?path=%2Fhomepagecampaigns%2F_doc%2F214358&method=PUT", entity,
                String.class);
       */

       System.out.println("Sdssdas");
    }
}
