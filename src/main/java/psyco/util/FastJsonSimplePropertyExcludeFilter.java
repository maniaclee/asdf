package psyco.util;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;

import java.util.HashSet;
import java.util.Set;

public class FastJsonSimplePropertyExcludeFilter implements PropertyPreFilter {
    private final Class<?> clazz;
    private final Set<String> excludes;

    public FastJsonSimplePropertyExcludeFilter(String... properties) {
        this((Class) null, properties);
    }

    public FastJsonSimplePropertyExcludeFilter(Class<?> clazz, String... properties) {
        this.excludes = new HashSet();
        this.clazz = clazz;
        for(String s : properties)
            this.excludes.add(s);

    }

    public Class<?> getClazz() {
        return this.clazz;
    }

    public Set<String> getExcludes() {
        return this.excludes;
    }

    public boolean apply(JSONSerializer serializer, Object source, String name) {
        return source == null ? true : (this.clazz != null && !this.clazz.isInstance(source) ? true : !this.excludes.contains(name));
    }
}
