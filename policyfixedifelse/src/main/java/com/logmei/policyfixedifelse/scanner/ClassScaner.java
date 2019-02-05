package com.logmei.policyfixedifelse.scanner;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 15:47 2019/2/3
 * @ Description：注解扫描器
 * @ Modified By：
 * @Version: 1.0.0
 */
public class ClassScaner implements ResourceLoaderAware {
    private ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
    private final List<TypeFilter> includeFilters = new LinkedList<>();
    private final List<TypeFilter> excludeFilters = new LinkedList<>();
    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(patternResolver);
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
       this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
       this.patternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);

    }

    public ClassScaner() {
    }

    public final ResourceLoader getResourceLoader(){
        return this.patternResolver;
    }

    public void addIncludeFilters(TypeFilter typeFilter){
        includeFilters.add(typeFilter);
    }

    public void addExcludeFilters(TypeFilter typeFilter){
        this.excludeFilters.add(0,typeFilter);
    }

    public void resetFilters(boolean useDefaultFilter){
        this.includeFilters.clear();
        this.excludeFilters.clear();
    }

    @SafeVarargs
    public static Set<Class<?>> scaner(String basePackage, Class<? extends Annotation>... annotation ){
      ClassScaner cs =new ClassScaner();
      for(Class c : annotation){
          cs.addIncludeFilters(new AnnotationTypeFilter(c));
      }
      return cs.doScan(basePackage);
    }
    @SafeVarargs
    public static Set<Class> scaner(String[] basePackages,Class<? extends Annotation>... annotation){
        ClassScaner cs = new ClassScaner();
        for (Class c : annotation) cs.addIncludeFilters(new AnnotationTypeFilter(c));
        Set<Class> classes = new HashSet<Class>();
        for (String s:basePackages) classes.addAll(cs.doScan(s));
        return classes;
    }

    public Set<Class<?>> doScan(String basePackage){
        Set<Class<?>> classes = new HashSet<Class<?>>();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage))
                +"/*Handler.class";
        try {
            Resource[] resources = this.patternResolver.getResources(packageSearchPath);
            for(int i=0;i<resources.length;i++){
                Resource resource = resources[i];
                if(resource.isReadable()){
                    MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                    if((includeFilters.size()==0 && excludeFilters.size()==0)|| matches(metadataReader)){
                        try {
                            classes.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning",e);
        }
        return classes;
    }

    protected boolean matches(MetadataReader metadataReader) throws IOException{
        for (TypeFilter tf : excludeFilters){
            if(tf.match(metadataReader,metadataReaderFactory)){
                return false;
            }
        }
        for (TypeFilter tf : includeFilters){
            if(tf.match(metadataReader,metadataReaderFactory)){
                return true;
            }
        }
        return false;
    }
}
