package com.pxccn.AutoAdder;/*
 * Classname:   com.pxccn.AutoAdder.BXML_Adder
 *
 * Version:     1.0
 *
 * Date:        5/14/2021
 *
 * Copyright (c) 2021 PHOENIX CONTACT
 */

import com.tridium.nre.util.IElement;

import javax.baja.naming.BOrd;
import javax.baja.naming.OrdTarget;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.status.BStatus;
import javax.baja.sys.*;
import javax.baja.xml.XElem;
import javax.baja.xml.XParser;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * TODO Description
 *
 * @author songyantao@phoenixcontact.com.cn
 * @version 1.0
 * @since 5/14/2021
 */

@NiagaraType
@NiagaraAction(name = "submit", defaultValue = "BString.DEFAULT", parameterType = "BString", facets = {@Facet(name = "BFacets.MULTI_LINE", value = "BBoolean.TRUE")})
@NiagaraProperty(name = "ver", defaultValue = "", type = "String", flags = Flags.READONLY | Flags.TRANSIENT)
public class BXML_Adder extends BComponent {



    /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
    /*@ $com.pxccn.AutoAdder.BXML_Adder(49147491)1.0$ @*/
    /* Generated Sat May 15 17:30:01 CST 2021 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "ver"
////////////////////////////////////////////////////////////////

    /**
     * Slot for the {@code ver} property.
     *
     * @see #getVer
     * @see #setVer
     */
    public static final Property ver = newProperty(Flags.READONLY | Flags.TRANSIENT, "", null);

    /**
     * Get the {@code ver} property.
     *
     * @see #ver
     */
    public String getVer() {
        return getString(ver);
    }

    /**
     * Set the {@code ver} property.
     *
     * @see #ver
     */
    public void setVer(String v) {
        setString(ver, v, null);
    }

////////////////////////////////////////////////////////////////
// Action "submit"
////////////////////////////////////////////////////////////////

    /**
     * Slot for the {@code submit} action.
     *
     * @see #submit(BString parameter)
     */
    public static final Action submit = newAction(0, BString.DEFAULT, BFacets.make(BFacets.MULTI_LINE, BBoolean.TRUE));

    /**
     * Invoke the {@code submit} action.
     *
     * @see #submit
     */
    public void submit(BString parameter) {
        invoke(submit, parameter, null);
    }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BXML_Adder.class);

    /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    public static final String VERSION = "0.01";

    public void started() {
        this.setVer(VERSION);
    }

    public void doSubmit(BString v) throws Exception {
        OperateDecoder decoder = new OperateDecoder(v.toString());
        decoder.readHeader();
        decoder.doIt(getRootComponent());
    }

    BComponent getRootComponent() {
        return (BComponent) this.getParent();
    }

    private Logger log = Logger.getLogger(getClass().getSimpleName());

    public BXML_Adder() {
    }


    public static class OperateDecoder {
        private final OperateElement root;

        public OperateDecoder(String xml) throws Exception {
            this.root = OperateElement.make(XParser.make(xml).parse());
        }

        public void readHeader() throws Exception {
            if (!this.root.name().equals("EmalyticsAutoWorker")) {
                throw new RuntimeException("Root element must be \"EmalyticsAutoWorker\"");
            }
            String ver = this.root.get("version");
            if (!ver.equals(VERSION)) {
                throw new RuntimeException("Unsupported version (" + ver + "): supported version is " + VERSION);
            }
        }

        public void doIt(BComponent rootComponent) {
            for (OperateElement n : this.root.nextDeepOperateElements()) {
                doProcess(rootComponent, n);
            }
        }

        static BValue doProcess(BValue currentCursor, OperateElement element) {
            return Op.valueOf(element.getOpCode()).doIt.Process(currentCursor, element);
        }

        static void goNext(BValue currentCursor, OperateElement element) {
            for (OperateElement n : element.nextDeepOperateElements()) {
                doProcess(currentCursor, n);
            }
        }
        static Method setPermanentlySubscribed_method = null;

        enum Op {
            //创建Component并添加，等同于 addSlot + newComponent
            createComponent((currentCursor, element) -> {
                CreateComponentElement e = CreateComponentElement.make(element);
                goNext(e.instance, e);
                try {
                    ((BComponent) currentCursor).add(e.slotName, e.instance, e.flag);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }),
            //获取指定的Slot
            getSlot((currentCursor, element) -> {
                GetSlotElement e = GetSlotElement.make(element);
                BValue value = ((BComplex) currentCursor).get(e.slotName);
                if (value == null) {
                    throw new IllegalArgumentException("can not get slot :" + e.slotName);
                }
                goNext(value, e);
                return null;
            }),
            //添加Slot,下一层级跟上实例
            addSlot((currentCursor, element) -> {
                AddSlotElement e = AddSlotElement.make(element);
                BValue value = doProcess(currentCursor, e.nextDeepOperateElements().get(0));
                try {
                    ((BComponent) currentCursor).add(e.slotName, value, e.flag);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }),
            //对指定的Slot赋值,下一层级跟上实例
            setSlot((currentCursor, element) -> {
                SetSlotElement e = SetSlotElement.make(element);
                BValue value;
                BValue defaultValue = null;
                if (((BComplex) currentCursor).getSlot(e.slotName).isProperty()) {
                    defaultValue = ((BComplex) currentCursor).getSlot(e.slotName).asProperty().getDefaultValue();
                }
                value = doProcess(defaultValue, e.nextDeepOperateElements().get(0));
                ((BComplex) currentCursor).set(e.slotName, value);
                if (e.flag != null) {
                    ((BComplex) currentCursor).setFlags(((BComplex) currentCursor).getSlot(e.slotName), e.flag);
                }
                return null;
            }),
            //创建Component，用于配合setValue或addSlot
            newComponent((defaultValue, element) -> {
                NewComponentElement e = NewComponentElement.make(element);
                goNext(e.instance, e);
                return e.instance;
            }),
            //创建已知的Simple,用于配合setValue或addSlot
            newSimple((defaultValue, element) -> {
                NewSimpleElement e = NewSimpleElement.make(element);
                if (e.EnumStr != null) {
                    return ((BFrozenEnum) defaultValue).getRange().get(e.EnumStr);
                } else {
                    return e.instance;
                }
            }),
            newSimple2((defaultValue, element) -> {
                NewSimpleElement2 e = NewSimpleElement2.make(element);
                try {
                    Type t = defaultValue.getType();
                    BValue ret = (BValue) t.getInstance();
                    Method m = defaultValue.getType().getTypeClass().getMethod("decodeFromString", String.class);
                    return (BValue) m.invoke(ret, e.decode);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }),
            setFlag((currentCursor, element) -> {
                SetFlagElement e = SetFlagElement.make(element);
                ((BComplex) currentCursor).setFlags(((BComplex) currentCursor).getSlot(e.slotName), e.flag);
                return null;
            }),
            link((c,element)->{
                LinkElement e = LinkElement.make(element);
                OrdTarget sourceTarget = e.from.resolve();
                BComponent sourceComp =sourceTarget.getComponent();
                Slot sourceSlot = sourceTarget.getSlotInComponent();

                OrdTarget targetTarget = e.to.resolve();
                BComponent targetComp =targetTarget.getComponent();
                Slot targetSlot = targetTarget.getSlotInComponent();

                targetComp.linkTo(e.linkName,sourceComp,sourceSlot,targetSlot);
                return null;
            }),
//            permanentlySubscribe((c,element)->{
//                PermanentlySubscribeElement e = PermanentlySubscribeElement.make(element);
//                if (setPermanentlySubscribed_method == null){
//                    try {
//                        setPermanentlySubscribed_method = BComponent.class.getDeclaredMethod("setPermanentlySubscribed", Boolean.TYPE);
//                        setPermanentlySubscribed_method.setAccessible(true);
//                    }catch(NoSuchMethodException ex){
//                        throw new RuntimeException(ex);
//                    }
//                }
//                try {
//                    setPermanentlySubscribed_method.invoke(e.comp, e.value);
//                }catch(Exception ex){
//                    ex.printStackTrace();
//                }
//                return null;
//            })


            ;

            final Handler doIt;

            Op(Handler doIt) {
                this.doIt = doIt;
            }
        }

        interface Handler {
            BValue Process(BValue currentCursor, OperateElement element);
        }




    }


    public static class NewComponentElement extends OperateElement {

        private NewComponentElement(XElem elem) {
            super(elem);
            String moduleName = this.get("m", null);
            String typeName;
            if (moduleName == null) {
                String tStr = this.get("t");
                int pos = tStr.indexOf(":");
                if (pos < 0) {
                    throw new RuntimeException("wrong type define !");
                }
                moduleName = tStr.substring(0, pos);
                typeName = tStr.substring(pos + 1);
            } else {
                typeName = this.get("t");
            }
            this.moduleName = moduleName;
            this.typeName = typeName;
            this.instance = Util.getNewBValueInstance(this.moduleName, this.typeName);
        }

        public static NewComponentElement make(OperateElement elem) {
            return elem == null ? null : new NewComponentElement(elem.getXmlElement());
        }

        public final String moduleName;
        public final String typeName;
        public final BValue instance;
    }

    public static class NewSimpleElement2 extends OperateElement {

        private NewSimpleElement2(XElem elem) {
            super(elem);
            this.decode = elem.text().toString();
        }

        public static NewSimpleElement2 make(OperateElement elem) {
            return elem == null ? null : new NewSimpleElement2(elem.getXmlElement());
        }

        public final String decode;
    }

    public static class LinkElement extends OperateElement {

        private LinkElement(XElem elem) {
            super(elem);
            this.from = BOrd.make(this.get("from"));
            this.to = BOrd.make(this.get("to"));
            this.linkName = this.get("n",null);
        }

        public static LinkElement make(OperateElement elem) {
            return elem == null ? null : new LinkElement(elem.getXmlElement());
        }
        public final BOrd from;
        public final BOrd to;
        public final String linkName;
    }

//    public static class PermanentlySubscribeElement extends OperateElement {
//
//        private PermanentlySubscribeElement(XElem elem) {
//            super(elem);
//            this.comp = BOrd.make(this.get("ord")).resolve().get().asComponent();
//            this.value = this.getb("v");
//        }
//
//        public static PermanentlySubscribeElement make(OperateElement elem) {
//            return elem == null ? null : new PermanentlySubscribeElement(elem.getXmlElement());
//        }
//        public final BComponent comp;
//        public final boolean value;
//    }


    public static class NewSimpleElement extends OperateElement {

        private NewSimpleElement(XElem elem) {
            super(elem);
            this.typeName = this.get("t");
            String e = null;
            Integer bs = null;
            switch (this.typeName) {
                case "boolean":
                case "bool":
                case "BBoolean":
                    this.instance = BBoolean.make(this.getb("v"));
                    break;
                case "Integer":
                case "int":
                case "BInteger":
                    this.instance = BInteger.make(this.geti("v"));
                    break;
                case "Double":
                case "double":
                case "BDouble":
                    this.instance = BDouble.make(this.getd("v"));
                    break;
                case "Float":
                case "float":
                case "BFloat":
                    this.instance = BFloat.make(this.getf("v"));
                    break;
                case "String":
                case "string":
                case "BString":
                    this.instance = BString.make(this.get("v"));
                    break;
                case "BStatus":
                    this.instance = BStatus.make(this.geti("v"));
                    break;
                case "BFrozenEnum":
                    e = this.get("v");
                    this.instance = null;
                    break;
                case "BRelTime":
                    this.instance = BRelTime.make(this.getl("v"));
                    break;
                default:
                    this.instance = null;
            }
            this.EnumStr = e;
        }

        public static NewSimpleElement make(OperateElement elem) {
            return elem == null ? null : new NewSimpleElement(elem.getXmlElement());
        }

        public final String typeName;
        public final BValue instance;
        public final String EnumStr;
    }


    public static class SetSlotElement extends OperateElement {

        private SetSlotElement(XElem elem) {
            super(elem);
            this.slotName = this.get("n");
            if (this.geti("f", -1) != -1) {
                this.flag = this.geti("f");
            } else {
                this.flag = null;
            }
        }

        public static SetSlotElement make(OperateElement elem) {
            return elem == null ? null : new SetSlotElement(elem.getXmlElement());
        }

        public final String slotName;
        public final Integer flag;
    }

    public static class SetFlagElement extends OperateElement {

        private SetFlagElement(XElem elem) {
            super(elem);
            this.flag = elem.geti("f");
            this.slotName = elem.get("n");
        }

        public static SetFlagElement make(OperateElement elem) {
            return elem == null ? null : new SetFlagElement(elem.getXmlElement());
        }

        public final int flag;
        public final String slotName;
    }


    public static class AddSlotElement extends OperateElement {

        private AddSlotElement(XElem elem) {
            super(elem);
            this.slotName = this.get("n") + "?";
            this.flag = this.geti("f", 0);

        }

        public static AddSlotElement make(OperateElement elem) {
            return elem == null ? null : new AddSlotElement(elem.getXmlElement());
        }

        public final String slotName;
        public final int flag;
    }

    public static class GetSlotElement extends OperateElement {

        private GetSlotElement(XElem elem) {
            super(elem);
            this.slotName = this.get("n");
        }

        public static GetSlotElement make(OperateElement elem) {
            return elem == null ? null : new GetSlotElement(elem.getXmlElement());
        }

        public final String slotName;
    }

    public static class CreateComponentElement extends OperateElement {

        private CreateComponentElement(XElem elem) {
            super(elem);
            String moduleName = this.get("m", null);
            String typeName;
            if (moduleName == null) {
                String tStr = this.get("t");
                int pos = tStr.indexOf(":");
                if (pos < 0) {
                    throw new RuntimeException("wrong type define !");
                }
                moduleName = tStr.substring(0, pos);
                typeName = tStr.substring(pos + 1);
            } else {
                typeName = this.get("t");
            }
            this.moduleName = moduleName;
            this.typeName = typeName;
            this.slotName = this.get("n") + "?";
            this.flag = this.geti("f", 0);
            this.instance = Util.getNewBValueInstance(this.moduleName, this.typeName);
        }

        public static CreateComponentElement make(OperateElement elem) {
            return elem == null ? null : new CreateComponentElement(elem.getXmlElement());
        }

        public final String moduleName;
        public final String typeName;
        public final String slotName;
        public final int flag;
        public final BValue instance;
    }

    public static class OperateElement implements IElement {
        private final XElem elem;

        private OperateElement(XElem elem) {
            this.elem = elem;
        }

        public static OperateElement make(XElem elem) {
            return elem == null ? null : new OperateElement(elem);
        }

        public String getOpCode() {
            return this.name();
        }

        public List<OperateElement> nextDeepOperateElements() {
            List<OperateElement> ret = new ArrayList<>();
            for (XElem e : this.elem.elems()) {
                ret.add(OperateElement.make(e));
            }
            return ret;
        }


        public String name() {
            return this.elem.name();
        }

        public String get(String attrName) {
            return this.elem.get(attrName);
        }

        public String get(String attrName, String def) {
            return this.elem.get(attrName, def);
        }

        public int geti(String attrName) {
            return this.elem.geti(attrName);
        }

        public int geti(String attrName, int def) {
            return this.elem.geti(attrName, def);
        }

        public double getd(String attrName) {
            return this.elem.getd(attrName);
        }

        public double getd(String attrName, double def) {
            return this.elem.getd(attrName, def);
        }

        public float getf(String attrName) {
            return this.elem.getf(attrName);
        }

        public float getf(String attrName, float def) {
            return this.elem.getf(attrName, def);
        }

        public long getl(String attrName) {
            return this.elem.getl(attrName);
        }

        public long getl(String attrName, long def) {
            return this.elem.getl(attrName, def);
        }

        public boolean getb(String attrName, boolean def) {
            return this.elem.getb(attrName, def);
        }

        public boolean getb(String attrName) {
            return this.elem.getb(attrName);
        }

        public int attrSize() {
            return this.elem.attrSize();
        }

        public String attrName(int index) {
            return this.elem.attrName(index);
        }

        public String attrValue(int index) {
            return this.elem.attrValue(index);
        }

        public IElement copy() {
            return make(this.elem.copy());
        }

        public String toString() {
            return this.elem.toString();
        }

        public XElem getXmlElement() {
            return this.elem;
        }
    }


}