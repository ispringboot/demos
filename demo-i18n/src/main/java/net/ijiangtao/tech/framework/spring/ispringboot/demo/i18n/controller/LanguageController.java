package net.ijiangtao.tech.framework.spring.ispringboot.demo.i18n.controller;

import net.ijiangtao.tech.framework.spring.ispringboot.demo.i18n.util.LanguageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ijiangtao.net
 */
@Controller
public class LanguageController {

    @GetMapping("/lang/current")
    @ResponseBody
    public String currentLanguage(){
        return LanguageUtil.getCurrentLang();
    }
}
