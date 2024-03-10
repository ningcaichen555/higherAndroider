package com.zx.annotation_compile;

import com.google.auto.service.AutoService;
import com.zx.annotations.BindView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class BindViewProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        printLog("process");

        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        HashMap<String, List<VariableElement>> variableElements = new HashMap<>();

        for(Element element : elementsAnnotatedWith){
            String activityName = element.getEnclosingElement().getSimpleName().toString();

            List<VariableElement> variableElementsList = variableElements.get(activityName);
            if (variableElementsList == null){
                variableElementsList = new ArrayList<>();
                variableElements.put(activityName,variableElementsList);
            }
            variableElementsList.add((VariableElement) element);
        }

        printLog("variableElements==="+ variableElements);
        return true;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        printLog("init");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportTypes =  new HashSet<>();
        supportTypes.add(BindView.class.getCanonicalName());
        return supportTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    private void printLog(String log){
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,log);
    }
}
